package com.github.zhangdi.dart.generator.generation;

import com.intellij.openapi.ui.Messages;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.lang.dart.DartComponentType;
import com.jetbrains.lang.dart.ide.generation.BaseCreateMethodsFix;
import com.jetbrains.lang.dart.ide.generation.BaseDartGenerateHandler;
import com.jetbrains.lang.dart.psi.DartClass;
import com.jetbrains.lang.dart.psi.DartComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GenerateFromMapHandler extends BaseDartGenerateHandler {
    @Override
    protected @NotNull BaseCreateMethodsFix createFix(@NotNull DartClass dartClass) {
        return new FromMapFix(dartClass);
    }

    @Override
    protected @NotNull String getTitle() {
        return "fromMap";
    }

    @Override
    protected void collectCandidates(@NotNull DartClass dartClass, @NotNull List<DartComponent> list) {
        if (dartClass == null) {
            Messages.showErrorDialog("dartClass null", "Dart Generator");
        }

        if (list == null) {
            Messages.showErrorDialog("list null", "Dart Generator");
        }

        list.addAll(ContainerUtil.findAll(this.computeClassMembersMap(dartClass, false).values(), (component) -> {
            return DartComponentType.typeOf(component) == DartComponentType.FIELD;
        }));
    }
}
