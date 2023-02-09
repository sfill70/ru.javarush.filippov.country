package ru.javarush.country;

import ru.javarush.country.entity.City;
import ru.javarush.country.handler.CityHandler;
import ru.javarush.country.redis.handlerRedis.RedisHandler;
import ru.javarush.country.util.Util;

import java.util.List;

import static java.util.Objects.nonNull;

public class Main {

    private final CityHandler cityHandler;
    private final RedisHandler redisHandler;

    public Main() {
        cityHandler = new CityHandler();
        redisHandler = new RedisHandler();
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<City> allCities = main.getAllCities();
        main.pushToRedis(allCities);

        List<Integer> ids = Util.getRandomIntList(main.grtCountCity(), 800);
        System.out.println(ids.size());

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

    private List<City> getAllCities(){
        return cityHandler.fetchData();
    }

    private void pushToRedis(List<City> cityList){
        redisHandler.pushToRedis(cityList);
    }

    private void testMysqlData (List<Integer> ids){
        cityHandler.testMysqlData(ids);
    }

    private void testRedisData(List<Integer> ids){
        redisHandler.testRedisData(ids);
    }

    private int grtCountCity(){
        return cityHandler.getCountCity();
    }

    private void shutdown() {
        cityHandler.shutdown();
        redisHandler.shutdown();
    }
}