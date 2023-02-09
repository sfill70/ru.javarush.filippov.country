package ru.javarush.country.redis.connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.hibernate.SessionFactory;
import ru.javarush.country.factory.MySessionFactory;

public class RedisClientFactory {
    private final RedisClient redisClient;

    private static RedisClientFactory instance;

    private RedisClientFactory() {
        redisClient = prepareRedisClient();
    }

    public static RedisClient getRedisClient() {
        if (instance == null) {
            instance = new RedisClientFactory();
        }
        return instance.redisClient;
    }

    @SuppressWarnings("unused")
    public io.lettuce.core.RedisClient prepareRedisClient() {
        io.lettuce.core.RedisClient redisClient = io.lettuce.core.RedisClient.create(RedisURI.create("localhost", 8001));
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("\nConnected to Redis\n");
        }
        return redisClient;
    }
}
