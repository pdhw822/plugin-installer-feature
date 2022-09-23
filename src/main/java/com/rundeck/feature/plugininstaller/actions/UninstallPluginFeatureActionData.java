package com.rundeck.feature.plugininstaller.actions;

public class UninstallPluginFeatureActionData {
    public String plugin;
    public String version;

    public UninstallPluginFeatureActionData() {
    }

    public UninstallPluginFeatureActionData(String plugin, String version) {
        this.plugin = plugin;
        this.version = version;
    }
}
