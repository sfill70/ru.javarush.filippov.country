package ru.javarush.country.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.Country;
import ru.javarush.country.factory.MySessionFactory;

import java.util.List;

import static java.util.Objects.nonNull;

public class CountryService {
    private final SessionFactory sessionFactory;
    private final CountryDao countryDao;

    public CountryService() {
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
        private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }

}
