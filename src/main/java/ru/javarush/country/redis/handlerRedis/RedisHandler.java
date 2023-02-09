package ru.javarush.country.redis.handlerRedis;

import ru.javarush.country.entity.City;
import ru.javarush.country.redis.mapper.CityCountryMapper;
import ru.javarush.country.redis.redisDao.RedisDao;

import java.util.List;

public class RedisHandler {
    private final CityCountryMapper cityCountryMapper;
    private final RedisDao redisDao;

    public RedisHandler() {
        cityCountryMapper = new CityCountryMapper();
        redisDao = new RedisDao();
    }

    public void pushToRedis(List<City> cityList){
        redisDao.pushToRedis(cityCountryMapper.transformData(cityList));
    }

    public void testRedisData(List<Integer> ids){
        redisDao.testRedisData(ids);
    }

    public void shutdown() {
        redisDao.shutdown();
    }
}
