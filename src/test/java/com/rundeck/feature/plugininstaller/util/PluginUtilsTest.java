package com.rundeck.feature.plugininstaller.util;

import com.rundeck.feature.plugininstaller.PluginInstallerFeatureConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PluginUtilsTest {

    @Test
    void downloadPluginList() throws IOException, InterruptedException {
        var cfg = new PluginInstallerFeatureConfig();
        var pluginList = PluginUtils.downloadPluginList(cfg);

        assertEquals(pluginList.plugins.size(), 3);
    }

    @Test
    void dowloadPluginTo() throws IOException, InterruptedException {
        var cfg = new PluginInstallerFeatureConfig();
        PluginUtils.downloadPluginTo(cfg, "http-step","1.0.12","/tmp/z_plugin.ajr");

    }
}