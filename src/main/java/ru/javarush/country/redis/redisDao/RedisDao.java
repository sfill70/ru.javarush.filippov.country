package ru.javarush.country.redis.redisDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import ru.javarush.country.redis.connection.RedisClientFactory;
import ru.javarush.country.redis.entityRedis.CityCountry;

import java.util.List;

import static java.util.Objects.nonNull;

public class RedisDao {
    private final ObjectMapper mapper;
    private final RedisClient redisClient;

    public RedisDao() {
        mapper = new ObjectMapper();
        redisClient = RedisClientFactory.getRedisClient();
    }

    public void pushToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }
}
