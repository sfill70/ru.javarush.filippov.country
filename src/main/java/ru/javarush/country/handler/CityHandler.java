package ru.javarush.country.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.factory.MySessionFactory;
import ru.javarush.country.redis.handlerRedis.RedisHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public class CityHandler {
    private final SessionFactory sessionFactory;
    private final CityDao cityDao;

    private final CountryDao countryDao;

    private final RedisHandler redisHandler;

    public CityHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        redisHandler = new RedisHandler();
    }

    public List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDao.getAll();
            int totalCount = cityDao.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDao.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = cityDao.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }

    public int getCountCity() {
        int countCity = 0;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countCity = cityDao.getTotalCount();
            session.getTransaction().commit();
            return countCity;
        }
    }

    public City getRandomCity() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            City city = cityDao.getRandomItem();
            session.getTransaction().commit();
            return city;
        }
    }

    public void pushToRedis(List<City> cityList) {
        redisHandler.pushToRedis(cityList);
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        CityHandler cityHandler = new CityHandler();
        List<City> cityList = cityHandler.fetchData();
        for (City city : cityList
        ) {
            System.out.println(city.getName());
        }
        System.out.println(cityList.size());
        cityHandler.pushToRedis(cityList);

    }

}
