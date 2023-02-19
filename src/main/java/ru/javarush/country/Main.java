package ru.javarush.country;

import ru.javarush.country.entity.City;
import ru.javarush.country.redis.serviceRedis.RedisService;
import ru.javarush.country.service.MySqlService;
import ru.javarush.country.util.Util;

import java.util.List;

public class Main {
    private final RedisService redisService;

    private final MySqlService mySqlService;

    public Main() {
        redisService = new RedisService();
        mySqlService = new MySqlService();
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
        return mySqlService.fetchData();
    }

    private void pushToRedis(List<City> cityList) {
        redisService.pushToRedis(cityList);
    }

    private void testMysqlData(List<Integer> ids) {
        mySqlService.testMysqlData(ids);
    }

    private void testRedisData(List<Integer> ids) {
        redisService.testRedisData(ids);
    }

    private int getCountCity() {
        return mySqlService.getCountCity();
    }

    private void shutdown() {
        mySqlService.shutdown();
        redisService.shutdown();
    }
}