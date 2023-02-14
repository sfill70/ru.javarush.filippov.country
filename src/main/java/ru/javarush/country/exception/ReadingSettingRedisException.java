package ru.javarush.country.exception;

import java.io.IOException;

public class ReadingSettingRedisException extends IOException {
    String additionalMessage;

    private static final String message = "Failed to read settings file. ";

    public ReadingSettingRedisException() {
        super(message);
    }


    public ReadingSettingRedisException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getMessage() {
        return message + additionalMessage;
    }
}
