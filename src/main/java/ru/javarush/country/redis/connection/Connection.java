package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Connection {
    private final ConnectionSetting connectionSetting;
    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static final Logger logger = LoggerFactory.getLogger(Connection.class);
    private final File xmlSetting = new File(classLoader.getResource("settingsRedis.xml").getFile());
    private final File yamlSetting = new File(classLoader.getResource("settingsRedis.yaml").getFile());

    /*experiment connecting to the database by reading the settings from .xml or .yaml file
    * left both options*/
    public Connection() {
        ConnectionSetting connectionSettingLoading = null;
        ObjectMapper mapperXlm = new XmlMapper();
        mapperXlm.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            connectionSettingLoading = mapperXlm.readValue(xmlSetting, ConnectionSetting.class);
        } catch (IOException e) {
            ObjectMapper mapperYaml = new YAMLMapper();
            mapperYaml.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                connectionSettingLoading = mapperYaml.readValue(yamlSetting, ConnectionSetting.class);
            } catch (IOException ex) {
                logger.error("Failed to read settings file. " + ex.toString());
            }
        }
        assert connectionSettingLoading != null;
        logger.info("Setup File Data Redis - "+connectionSettingLoading.toString());
        this.connectionSetting = connectionSettingLoading;
    }

    public String getConnectionHost() {
        return connectionSetting.getHost();
    }

    public int getConnectionPort() {
        return connectionSetting.getPort();
    }

    public int getNumberDb(){
        return connectionSetting.getNumberdb();
    }
}
