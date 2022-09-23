package com.rundeck.feature.plugininstaller.actions;

public class InstallPluginFeatureActionData {
    public String plugin;
    public String version;

    public InstallPluginFeatureActionData() {
    }

    public InstallPluginFeatureActionData(String plugin, String version) {
        this.plugin = plugin;
        this.version = version;
    }
}
