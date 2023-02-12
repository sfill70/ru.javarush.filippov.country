package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="class")
public class ConnectionSetting {
    String host;
    int port;

    public ConnectionSetting() {
    }

    public ConnectionSetting(String host, int port) {
        this.host = host;
        this.port = port;
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

    @Override
    public String toString() {
        return "ConnectionSetting{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
