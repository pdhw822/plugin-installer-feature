package com.rundeck.feature.plugininstaller;


import com.rundeck.feature.api.Feature;
import com.rundeck.feature.api.action.FeatureAction;
import com.rundeck.feature.plugininstaller.actions.InstallPluginFeatureAction;
import com.rundeck.feature.plugininstaller.actions.UninstallPluginFeatureAction;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class PluginInstallerFeature implements Feature<PluginInstallerFeatureConfig> {

    boolean enable;

    Map<String,FeatureAction<?>> actions = Map.of(InstallPluginFeatureAction.NAME, new InstallPluginFeatureAction(), UninstallPluginFeatureAction.NAME, new UninstallPluginFeatureAction());

    PluginInstallerFeatureConfig config = new PluginInstallerFeatureConfig();

    @Override
    public String getName() {
        return "plugin-installer";
    }

    @Override
    public String getDescription() {
        return "Install Rundeck Plugins";
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public void enable() {
        enable = true;
    }

    @Override
    public void disable() {
        enable = false;
    }

    @Override
    public void cleanup() {

    }

    @Override
    public Collection<FeatureAction<?>> getActions() {
        return actions.values();
    }

    @Override
    public Optional<FeatureAction<?>> getActionByName(String actionName) {
        return Optional.ofNullable(actions.get(actionName));
    }

    @Override
    public void configure(PluginInstallerFeatureConfig pluginInstallerFeatureConfig) {
        this.config = pluginInstallerFeatureConfig;
    }

    @Override
    public PluginInstallerFeatureConfig getConfiguration() {
        return config;
    }

    @Override
    public Class<PluginInstallerFeatureConfig> getConfigClass() {
        return PluginInstallerFeatureConfig.class;
    }
}
