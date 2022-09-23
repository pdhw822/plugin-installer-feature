package com.rundeck.feature.plugininstaller.actions;

import com.rundeck.feature.api.action.FeatureAction;
import com.rundeck.feature.api.context.FeatureActionContext;
import com.rundeck.feature.api.model.CompletionStatus;

public class UninstallPluginFeatureAction implements FeatureAction<UninstallPluginFeatureActionData> {
    public static final String NAME = "uninstall-plugin";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "Uninstall a plugin";
    }

    @Override
    public CompletionStatus execute(FeatureActionContext featureActionContext) {
        return CompletionStatus.SUCCESS;
    }

    @Override
    public Class<UninstallPluginFeatureActionData> getFeatureActionDataClass() {
        return UninstallPluginFeatureActionData.class;
    }

    @Override
    public UninstallPluginFeatureActionData getSampleActionData() {
        return new UninstallPluginFeatureActionData("http-step","1.0.12");
    }
}
