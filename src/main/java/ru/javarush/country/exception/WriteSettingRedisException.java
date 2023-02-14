package ru.javarush.country.exception;

import java.io.IOException;

public class WriteSettingRedisException extends IOException {
    String additionalMessage;

    private static final String message = "Failed to read settings file. ";

    public WriteSettingRedisException() {
        super(message);
    }


    public WriteSettingRedisException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getMessage() {
        return message + additionalMessage;
    }
}
