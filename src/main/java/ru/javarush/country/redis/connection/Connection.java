package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class Connection {
    private final ConnectionSetting connectionSetting;
    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private final File file = new File(classLoader.getResource("settingsRedis.xml").getFile());

    public Connection() {
        ObjectMapper mapperXlm = new XmlMapper();
        mapperXlm.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            this.connectionSetting = mapperXlm.readValue(file, ConnectionSetting.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConnectionHost() {
        return connectionSetting.getHost();
    }

    public int getConnectionPort() {
        return connectionSetting.getPort();
    }
}
