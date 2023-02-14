package ru.javarush.country.redis.connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedisClientFactory {
    private final RedisClient redisClient;
    private final Connection connection;
    private final String redisURI;
    private static RedisClientFactory instance;

    private static final Logger logger = LoggerFactory.getLogger(RedisClientFactory.class);

    private RedisClientFactory() {
        this.connection = new Connection();
        this.redisURI = getRedisURI(connection.getConnectionHost(), connection.getConnectionPort(),
                connection.getNumberDb());
        this.redisClient = prepareRedisClient();
    }

    public static RedisClient getRedisClient() {
        if (instance == null) {
            instance = new RedisClientFactory();
        }
        return instance.redisClient;
    }

    @SuppressWarnings("unused")
    public RedisClient prepareRedisClient() {
//        RedisClient redisClient = RedisClient.create(RedisURI.create(connection.getConnectionHost(), connection.getConnectionPort()));
        RedisClient redisClient = RedisClient.create(redisURI);

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("\nConnected to Redis\n");
            logger.info("\nConnected to Redis\n");
        } catch (Exception e) {
            logger.error("Failed to connect to database - " + e.getMessage());
        }

        return redisClient;
    }

    public String getRedisURI(String host, int port, int numberdb) {
        return "redis://" + connection.getConnectionHost()
                + ":" + connection.getConnectionPort() + "/" + connection.getNumberDb();
    }
}
