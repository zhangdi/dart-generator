package com.github.zhangdi.dart.generator.configuration;

import com.intellij.openapi.project.Project;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel {
    Project mProject;
    ConfigurationData mData;
    ConfigurationData mLastData;
    JPanel mPanel;
    Integer mLine = 0;

    JTextField mToMapMethodNameTextField;
    JTextField mFromMapMethodNameTextField;

    SettingsPanel(Project project) {
        mProject = project;
        mData = ConfigurationData.getInstance(project);
        mLastData = ConfigurationData.getInstance(project);

        mPanel = new JPanel(new GridBagLayout());

        mToMapMethodNameTextField = new JTextField(mData.toMapMethodName);
        mFromMapMethodNameTextField = new JTextField(mData.fromMapMethodName);

        addTextFieldLine("Name of toMap method", mToMapMethodNameTextField);
        addTextFieldLine("Name of fromMap method", mFromMapMethodNameTextField);


        addPlaceholder();
    }

    void addPlaceholder() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        // Remaining space
        mPanel.add(
                new JPanel(),
                constraints);
    }

    void addTextFieldLine(String label, JTextField textField) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        labelConstraints.gridx = 0;
        labelConstraints.gridy = mLine;
        labelConstraints.insets = JBUI.insetsRight(10);

        mPanel.add(new JLabel(label), labelConstraints);

        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.gridx = 2;
        fieldConstraints.gridy = mLine;
        fieldConstraints.weightx = 0.8;
        fieldConstraints.insets = JBUI.emptyInsets();

        mPanel.add(textField, fieldConstraints);

        mLine++;
    }


    JComponent getComponent() {
        return mPanel;
    }

    void apply() {
        mData.toMapMethodName = mToMapMethodNameTextField.getText();
        mData.fromMapMethodName = mFromMapMethodNameTextField.getText();

        mData.save();
    }

    void reset(){
        mData.reset();
    }

    Boolean isModified() {
        return mData.equals(mLastData);
    }
}
