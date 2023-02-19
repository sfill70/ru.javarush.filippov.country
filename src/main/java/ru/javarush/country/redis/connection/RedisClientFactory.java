package ru.javarush.country.redis.connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedisClientFactory {
    private final RedisClient redisClient;
    private final ConnectionSetting connectionSetting;
    private final String redisURI;
    private static RedisClientFactory instance;

    private static final Logger logger = LoggerFactory.getLogger(RedisClientFactory.class);

    private RedisClientFactory() {
        this.connectionSetting = new ConnectionSetting().getConnectionSetting();
        this.redisURI = getRedisURI(connectionSetting.getHost(), connectionSetting.getPort(),
                connectionSetting.getNumberdb());
        this.redisClient = prepareRedisClient();
    }

    public static RedisClient getRedisClient() {
        if (instance == null) {
            instance = new RedisClientFactory();
        }
        return instance.redisClient;
    }

    @SuppressWarnings("unused")
    public RedisClient prepareRedisClient() {RedisClient redisClient = RedisClient.create(redisURI);

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            logger.info("\nConnected to Redis\n");
        } catch (Exception e) {
            logger.error("Failed to connect to database - " + e.getMessage());
            redisClient = RedisClient.create(RedisURI.create(connectionSetting.getHost(), connectionSetting.getPort()));
            try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
                logger.info("\nConnected to Redis\n");
            }catch (Exception ex){
                logger.error("Failed to connect to database - " + e.getMessage());
            }
        }
        return redisClient;
    }

    public String getRedisURI(String host, int port, int numberdb) {
        return "redis://" + host
                + ":" + port + "/" + numberdb;
    }
}
