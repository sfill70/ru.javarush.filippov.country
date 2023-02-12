package ru.javarush.country.redis.connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;


public class RedisClientFactory {
    private final RedisClient redisClient;
    private final Connection connection;
    private static RedisClientFactory instance;

    private RedisClientFactory() {
        this.connection = new Connection();
        this.redisClient = prepareRedisClient();
    }

    public static RedisClient getRedisClient() {
        if (instance == null) {
            instance = new RedisClientFactory();
        }
        return instance.redisClient;
    }

    @SuppressWarnings("unused")
    public io.lettuce.core.RedisClient prepareRedisClient() {
        RedisClient redisClient = io.lettuce.core.RedisClient.create(RedisURI.create(connection.getConnectionHost(), connection.getConnectionPort()));
//        RedisClient redisClient = io.lettuce.core.RedisClient.create(RedisURI.create("127.0.0.1", 8001));

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("\nConnected to Redis\n");
        }
        return redisClient;
    }
}
