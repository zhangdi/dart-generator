package com.github.zhangdi.dart.generator.generation;

import com.github.zhangdi.dart.generator.configuration.ConfigurationData;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TextExpression;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.lang.dart.ide.generation.BaseCreateMethodsFix;
import com.jetbrains.lang.dart.psi.DartClass;
import com.jetbrains.lang.dart.psi.DartComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Set;

public class ToMapFix extends BaseCreateMethodsFix<DartComponent> {
    ConfigurationData mData;

    public ToMapFix(@NotNull DartClass dartClass) {
        super(dartClass);
    }

    @Override
    protected @NotNull String getNothingFoundMessage() {
        return "nothing found";
    }

    protected void processElements(@NotNull Project project, @NotNull Editor editor, @NotNull Set<DartComponent> elementsToProcess) {
        if (project == null) {
            Messages.showErrorDialog("project is null", "Dart Generator");
        }

        mData = ConfigurationData.getInstance(project);

        if (editor == null) {
            Messages.showErrorDialog("editor is null", "Dart Generator");
        }

        if (elementsToProcess == null) {
            Messages.showErrorDialog("elementsToProcess is null", "Dart Generator");
        }

        TemplateManager templateManager = TemplateManager.getInstance(project);
        this.anchor = this.doAddMethodsForOne(editor, templateManager, this.buildFunctionsText(templateManager, elementsToProcess), this.anchor);
    }

    protected Template buildFunctionsText(TemplateManager templateManager, Set<DartComponent> elementsToProcess) {
        Template template = templateManager.createTemplate(this.getClass().getName(), "Dart");
        template.setToReformat(true);

        template.addTextSegment("Map<String, dynamic>");
        template.addTextSegment(" ");
        template.addVariable(new TextExpression(mData.toMapMethodName), true);
        template.addTextSegment("()");
        template.addTextSegment("{");

        template.addTextSegment("return {");
        Iterator iterator = elementsToProcess.iterator();

        while (iterator.hasNext()) {
            DartComponent component = (DartComponent) iterator.next();
            template.addTextSegment("'");
            template.addTextSegment(component.getName());
            template.addTextSegment("'");
            template.addTextSegment(":");
            template.addTextSegment(component.getName());
            template.addTextSegment(",");
        }

        template.addTextSegment("};");

        template.addTextSegment("}");
        template.addEndVariable();
        template.addTextSegment(" ");
        return template;
    }

    @Override
    protected @Nullable Template buildFunctionsText(TemplateManager templateManager, DartComponent dartComponent) {
        return null;
    }
}
