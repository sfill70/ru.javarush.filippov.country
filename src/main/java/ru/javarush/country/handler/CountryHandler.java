package ru.javarush.country.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.factory.MySessionFactory;

import java.util.List;

public class CountryHandler {
    private final SessionFactory sessionFactory;
    private final CountryDao countryDao;

    public CountryHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        countryDao = new CountryDao(sessionFactory);
    }

    public static void main(String[] args) {
        CountryHandler countryHandler = new CountryHandler();
        for (Country country : countryHandler.getCountries(20,40)
                ) {
            System.out.println(country.getLocalName());
        }
    }

    public List<Country> getCountries(int offset, int count) {
        List<Country> countries;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countries = countryDao.getItems(offset,count);
            session.getTransaction().commit();
            return countries;
        }
    }
}
