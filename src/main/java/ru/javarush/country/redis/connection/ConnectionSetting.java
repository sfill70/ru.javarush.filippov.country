package ru.javarush.country.redis.connection;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="class")
public class ConnectionSetting {
    String host;
    int port;
    int numberdb;

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

    @Override
    public String toString() {
        return "ConnectionSetting{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", numberDb=" + numberdb +
                '}';
    }
}
