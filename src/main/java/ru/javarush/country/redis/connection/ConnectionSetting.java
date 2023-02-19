package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class ConnectionSetting {
    String host;
    int port;
    int numberdb;
    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static final Logger logger = LoggerFactory.getLogger(ConnectionSetting.class);
    private final File yamlSetting = new File(classLoader.getResource("settingsRedis.yaml").getFile());

    public ConnectionSetting() {
    }

    public ConnectionSetting(String host, int port, int numberdb) {
        this.host = host;
        this.port = port;
        this.numberdb = numberdb;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getNumberdb() {
        return numberdb;
    }

    public void setNumberdb(int numberdb) {
        this.numberdb = numberdb;
    }

    public ConnectionSetting getConnectionSetting(){
        ConnectionSetting connectionSettingLoading = null;
        ObjectMapper mapperYaml = new YAMLMapper();
        mapperYaml.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            connectionSettingLoading = mapperYaml.readValue(yamlSetting, ConnectionSetting.class);
        } catch (IOException e) {
            logger.error("Failed to read settings file. " + e.toString());
        }
        logger.info("Setup File Data Redis - " + connectionSettingLoading.toString());
        return connectionSettingLoading;
    }

    @Override
    public String toString() {
        return "ConnectionSetting{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", numberDb=" + numberdb +
                '}';
    }
}
