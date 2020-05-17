package com.github.zhangdi.dart.generator.configuration;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

public class ConfigurationData {
    public static final String KEY_TO_MAP_METHOD_NAME = "dart-generator.to-map-method-name";
    public static final String KEY_FROM_MAP_METHOD_NAME = "dart-generator.from-map-method-name";

    public static final String DEFAULT_TO_MAP_METHOD_NAME = "toMap";
    public static final String DEFAULT_FROM_MAP_METHOD_NAME = "fromMap";

    public String toMapMethodName;
    public String fromMapMethodName;

    Project mProject;

    /**
     * @param project 项目
     * @return 配置数据
     */
    public static ConfigurationData getInstance(Project project) {
        PropertiesComponent properties = PropertiesComponent.getInstance(project);

        ConfigurationData data = new ConfigurationData();
        data.mProject = project;
        data.toMapMethodName = properties.getValue(KEY_TO_MAP_METHOD_NAME, DEFAULT_TO_MAP_METHOD_NAME);
        data.fromMapMethodName = properties.getValue(KEY_FROM_MAP_METHOD_NAME, DEFAULT_FROM_MAP_METHOD_NAME);

        return data;
    }

    /**
     *
     */
    void save() {
        PropertiesComponent properties = PropertiesComponent.getInstance(mProject);

        properties.setValue(KEY_TO_MAP_METHOD_NAME, toMapMethodName);
        properties.setValue(KEY_FROM_MAP_METHOD_NAME, fromMapMethodName);
    }

    void reset() {
        toMapMethodName = DEFAULT_TO_MAP_METHOD_NAME;
        fromMapMethodName = DEFAULT_FROM_MAP_METHOD_NAME;

        save();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConfigurationData) {
            final ConfigurationData data = (ConfigurationData) obj;
            return data.fromMapMethodName.equals(fromMapMethodName) && data.toMapMethodName.equals(toMapMethodName);
        } else {
            return false;
        }
    }
}
