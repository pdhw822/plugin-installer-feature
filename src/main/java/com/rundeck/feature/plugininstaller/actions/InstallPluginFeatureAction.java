package com.rundeck.feature.plugininstaller.actions;

import com.rundeck.feature.api.action.FeatureAction;
import com.rundeck.feature.api.context.FeatureActionContext;
import com.rundeck.feature.api.event.ActionEventPublisher;
import com.rundeck.feature.api.model.CompletionStatus;
import com.rundeck.feature.api.output.OutputLevel;
import com.rundeck.feature.plugininstaller.PluginInstallerFeatureConfig;
import com.rundeck.feature.plugininstaller.event.LogOutputActionEvent;
import com.rundeck.feature.plugininstaller.models.PluginList;
import com.rundeck.feature.plugininstaller.util.PluginUtils;

import java.util.stream.Collectors;

public class InstallPluginFeatureAction implements FeatureAction<InstallPluginFeatureActionData> {
    public static final String NAME = "install-plugin";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "Install a plugin";
    }

    @Override
    public CompletionStatus execute(FeatureActionContext featureActionContext) {
        String aid = featureActionContext.getActionId();
        ActionEventPublisher evtPublisher = featureActionContext.getEventPublisher();
        try {
            InstallPluginFeatureActionData data = featureActionContext.get(FeatureActionContext.KEY_ACTION_DATA, InstallPluginFeatureActionData.class);
            PluginInstallerFeatureConfig config = featureActionContext.get(FeatureActionContext.KEY_FEATURE_CONFIG, PluginInstallerFeatureConfig.class);
            PluginList pluginList = PluginUtils.downloadPluginList(config);
            //TODO: plugin validation
            PluginUtils.downloadPluginTo(config, data.plugin, data.version, String.format("%s/libext/%s-%s.jar", System.getProperty("rdeck.base"), data.plugin, data.version));
            evtPublisher.publishOutput(new LogOutputActionEvent(aid, String.format("Installed plugin: %s:%s", data.plugin, data.version), OutputLevel.ERROR));
            return CompletionStatus.SUCCESS;
        } catch(Exception ex) {
            ex.printStackTrace();
            evtPublisher.publishOutput(new LogOutputActionEvent(aid, String.format("Failed to install plugin: %s",ex.getMessage()), OutputLevel.ERROR));
        }
        return CompletionStatus.ERROR;
    }

    @Override
    public Class<InstallPluginFeatureActionData> getFeatureActionDataClass() {
        return InstallPluginFeatureActionData.class;
    }

    @Override
    public InstallPluginFeatureActionData getSampleActionData() {
        return new InstallPluginFeatureActionData("http-step","1.0.12");
    }
}
