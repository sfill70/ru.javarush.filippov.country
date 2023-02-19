package ru.javarush.country;

import ru.javarush.country.entity.City;
import ru.javarush.country.service.CityService;
import ru.javarush.country.redis.serviceRedis.RedisService;
import ru.javarush.country.util.Util;

import java.util.List;

public class Main {

    private final CityService cityService;
    private final RedisService redisService;

    public Main() {
        cityService = new CityService();
        redisService = new RedisService();
    }

    public static void main(String[] args) {
        Main main = new Main();

        List<City> allCities = main.getAllCities();
        main.pushToRedis(allCities);

        List<Integer> ids = Util.getRandomIntList(main.getCountCity() - 1, 800);


        long startRedis = System.currentTimeMillis();
        main.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        main.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        main.shutdown();

    }

    private List<City> getAllCities() {
        return cityService.fetchData();
    }

    private void pushToRedis(List<City> cityList) {
        redisService.pushToRedis(cityList);
    }

    private void testMysqlData(List<Integer> ids) {
        cityService.testMysqlData(ids);
    }

    private void testRedisData(List<Integer> ids) {
        redisService.testRedisData(ids);
    }

    private int getCountCity() {
        return cityService.getCountCity();
    }

    private void shutdown() {
        cityService.shutdown();
        redisService.shutdown();
    }
}