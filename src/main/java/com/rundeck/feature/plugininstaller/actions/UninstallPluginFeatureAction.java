package com.rundeck.feature.plugininstaller.actions;

import com.rundeck.feature.api.action.FeatureAction;
import com.rundeck.feature.api.context.ContextKeys;
import com.rundeck.feature.api.context.FeatureActionContext;
import com.rundeck.feature.api.event.ActionEventPublisher;
import com.rundeck.feature.api.model.CompletionStatus;
import com.rundeck.feature.api.output.OutputLevel;
import com.rundeck.feature.plugininstaller.event.LogOutputActionEvent;

import java.nio.file.Files;
import java.nio.file.Path;

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
        String aid = featureActionContext.getActionId();
        String user = featureActionContext.getUser();
        ActionEventPublisher evtPublisher = featureActionContext.getEventPublisher();
        UninstallPluginFeatureActionData data = featureActionContext.get(ContextKeys.ACTION_DATA, UninstallPluginFeatureActionData.class);
        try {
            String file = String.format("%s/libext/%s-%s.jar", System.getProperty("rdeck.base"), data.plugin, data.version);
            Files.delete(Path.of(file));
            evtPublisher.publishOutput(new LogOutputActionEvent(aid, user, String.format("Uninstalled plugin: %s:%s", data.plugin, data.version)));
            return CompletionStatus.SUCCESS;
        } catch(Exception ex) {
            ex.printStackTrace();
            evtPublisher.publishOutput(new LogOutputActionEvent(aid, user, String.format("Failed to uninstall plugin: %s",ex.getMessage()), OutputLevel.ERROR));
        }
        return CompletionStatus.ERROR;
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
