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

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
