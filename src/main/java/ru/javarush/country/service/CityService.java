package ru.javarush.country.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.factory.MySessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public class CityService {
    private final SessionFactory sessionFactory;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private static final int STEP = 500;

    public CityService() {
        sessionFactory = MySessionFactory.getSessionFactory();
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
    }

    public List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDao.getAll();
            int totalCount = cityDao.getTotalCount();
            for (int i = 0; i < totalCount; i += STEP) {
                allCities.addAll(cityDao.getItems(i, STEP));
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

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }

}
