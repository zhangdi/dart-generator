package com.github.zhangdi.dart.generator.actions;

import com.github.zhangdi.dart.generator.generation.GenerateFromMapHandler;
import com.jetbrains.lang.dart.ide.generation.BaseDartGenerateHandler;
import org.jetbrains.annotations.NotNull;

public class FromMapAction extends MyAction {
    @Override
    protected @NotNull BaseDartGenerateHandler getGenerateHandler() {
        return new GenerateFromMapHandler();
    }
}
