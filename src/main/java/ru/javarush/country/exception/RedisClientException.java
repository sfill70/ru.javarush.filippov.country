package ru.javarush.country.exception;

public class RedisClientException extends Exception {
    String additionalMessage;
    private static final String message = "Failed to connect to Redis database, please check your connection settings. ";

    public RedisClientException() {
        super(message);
    }

    public RedisClientException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getMessage() {
        return message + additionalMessage;
    }
}
