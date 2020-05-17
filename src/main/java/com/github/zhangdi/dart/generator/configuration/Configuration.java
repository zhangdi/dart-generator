package com.github.zhangdi.dart.generator.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Configuration implements Configurable {
    Project mProject;
    SettingsPanel mPanel;

    Configuration(Project project) {
        mProject = project;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Dart Generator";
    }

    @Override
    public @Nullable JComponent createComponent() {
        if (mPanel == null) {
            mPanel = new SettingsPanel(mProject);
        }

        return mPanel.getComponent();
    }

    @Override
    public boolean isModified() {
        return mPanel != null && mPanel.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        if (mPanel != null) {
            mPanel.apply();
        }
    }

    @Override
    public void reset() {
        mPanel.reset();
    }
}
