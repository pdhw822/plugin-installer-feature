package com.rundeck.feature.plugininstaller.actions;

import com.rundeck.feature.api.action.FeatureAction;
import com.rundeck.feature.api.context.FeatureActionContext;
import com.rundeck.feature.api.event.ActionEventPublisher;
import com.rundeck.feature.api.model.CompletionStatus;
import com.rundeck.feature.plugininstaller.PluginInstallerFeatureConfig;
import com.rundeck.feature.plugininstaller.event.LogOutputActionEvent;
import com.rundeck.feature.plugininstaller.models.PluginList;
import com.rundeck.feature.plugininstaller.util.PluginUtils;

import java.util.stream.Collectors;

public class ListInstallablePluginsFeatureAction implements FeatureAction<Void> {
    @Override
    public String getName() {
        return "list-installable-plugins";
    }

    @Override
    public String getDescription() {
        return "List Plugins available for installation";
    }

    @Override
    public CompletionStatus execute(FeatureActionContext featureActionContext) {
        try {
            String aid = featureActionContext.getActionId();
            ActionEventPublisher evtPublisher = featureActionContext.getEventPublisher();
            PluginInstallerFeatureConfig config = featureActionContext.get(FeatureActionContext.KEY_FEATURE_CONFIG, PluginInstallerFeatureConfig.class);
            PluginList pluginList = PluginUtils.downloadPluginList(config);
            evtPublisher.publishOutput(new LogOutputActionEvent(aid, pluginList.plugins.stream().map(p -> p.name).collect(Collectors.joining("\n"))));
            return CompletionStatus.SUCCESS;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return CompletionStatus.ERROR;
    }

    @Override
    public Class<Void> getFeatureActionDataClass() {
        return Void.class;
    }

    @Override
    public Void getSampleActionData() {
        return null;
    }
}
