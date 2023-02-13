package ru.javarush.country.exception;

import java.io.IOException;

public class ConnectionRedisException extends IOException {
    String additionalMessage;

    private static final String message = "Failed to read settings file. ";

    public ConnectionRedisException() {
        super(message);
    }

    public ConnectionRedisException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    public String getAdditionalMessage() {
        return message + additionalMessage;
    }
}
