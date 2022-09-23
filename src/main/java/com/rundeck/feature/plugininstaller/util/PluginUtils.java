package com.rundeck.feature.plugininstaller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rundeck.feature.plugininstaller.PluginInstallerFeatureConfig;
import com.rundeck.feature.plugininstaller.models.PluginList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PluginUtils {
    public static PluginList downloadPluginList(PluginInstallerFeatureConfig config) throws IOException, InterruptedException {
        HttpClient hc = HttpClient.newHttpClient();
        var rsp = hc.send(HttpRequest.newBuilder().uri(URI.create(String.format("%s/%s",config.cdnBase, config.pluginCandidatesDef))).build(), HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(rsp.body(), PluginList.class);
    }

    public static void downloadPluginTo(PluginInstallerFeatureConfig config, String plugin, String version, String destination) throws IOException, InterruptedException {
        HttpClient hc = HttpClient.newHttpClient();
        String urlstr = String.format("%splugins/%s/%s/%2$s-%3$s.jar",config.cdnBase,plugin,version);
        //System.out.printf("copying to: %s\n",urlstr);
        var rsp = hc.send(HttpRequest.newBuilder().uri(URI.create(urlstr)).build(), HttpResponse.BodyHandlers.ofInputStream());
        rsp.body().transferTo(new FileOutputStream(destination));
    }
}
