package ru.javarush.country.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.Country;
import ru.javarush.country.factory.MySessionFactory;

import java.util.List;

import static java.util.Objects.nonNull;

public class CountryHandler {
    private final SessionFactory sessionFactory;
    private final CountryDao countryDao;

    public CountryHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        countryDao = new CountryDao(sessionFactory);
    }

    public List<Country> getAll() {
        List<Country> countries;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countries = countryDao.getAll();
            session.getTransaction().commit();
            return countries;
        }
    }

    public Country getRandomItem() {
        Country country = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            country = countryDao.getRandomItem();
            session.getTransaction().commit();
            return country;
        }
    }

    public List<Country> getCountries(int offset, int count) {
        List<Country> countries;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countries = countryDao.getItems(offset, count);
            session.getTransaction().commit();
            return countries;
        }
    }

    public int getTotalCount() {
        int count = 0;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = countryDao.getTotalCount();
            session.getTransaction().commit();
            return count;
        }
    }

        private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        CountryHandler countryHandler = new CountryHandler();
        Country country = countryHandler.getRandomItem();
        System.out.println(country.getLocalName());
        /*for (Country country : countryHandler.getCountries(20, 40)
        ) {
            System.out.println(country.getLocalName());
        }

        System.out.println(countryHandler.getTotalCount());

        for (Country country : countryHandler.getAll()
        ) {
            System.out.println(country.getLocalName());
        }*/
    }
}
