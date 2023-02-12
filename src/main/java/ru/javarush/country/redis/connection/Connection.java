package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

public class Connection {
    private final ConnectionSetting connectionSetting;
    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private final File xmlSetting = new File(classLoader.getResource("settingsRedis.xml").getFile());
    private final File yamlSetting = new File(classLoader.getResource("settingsRedis.yaml").getFile());

    public Connection() {
        ConnectionSetting connectionSettingLoading;
        ObjectMapper mapperXlm = new XmlMapper();
        mapperXlm.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            connectionSettingLoading = mapperXlm.readValue(xmlSetting, ConnectionSetting.class);
//            int x = 1/0;
        } catch (IOException e) {
            ObjectMapper mapperYaml = new YAMLMapper();
            mapperYaml.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                connectionSettingLoading = mapperYaml.readValue(yamlSetting, ConnectionSetting.class);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        this.connectionSetting = connectionSettingLoading;
    }

    public String getConnectionHost() {
        return connectionSetting.getHost();
    }

    public int getConnectionPort() {
        return connectionSetting.getPort();
    }
}
