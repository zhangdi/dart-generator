package com.github.zhangdi.dart.generator.actions;

import com.github.zhangdi.dart.generator.generation.GenerateToMapHandler;
import com.jetbrains.lang.dart.ide.generation.BaseDartGenerateHandler;
import org.jetbrains.annotations.NotNull;

public class ToMapAction extends MyAction {

    @Override
    protected @NotNull BaseDartGenerateHandler getGenerateHandler() {
        return new GenerateToMapHandler();
    }


}
