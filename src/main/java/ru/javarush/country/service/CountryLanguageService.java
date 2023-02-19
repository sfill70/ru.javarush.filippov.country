package ru.javarush.country.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryLanguageDao;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.factory.MySessionFactory;

import java.util.List;

import static java.util.Objects.nonNull;

public class CountryLanguageService {
    private final SessionFactory sessionFactory;
    private final CountryLanguageDao countryLanguageDao;

    public CountryLanguageService() {
        sessionFactory = MySessionFactory.getSessionFactory();
        countryLanguageDao = new CountryLanguageDao(sessionFactory);
    }

    public List<CountryLanguage> getCountries(int offset, int count) {
        List<CountryLanguage> countryLanguages;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countryLanguages = countryLanguageDao.getItems(offset,count);
            session.getTransaction().commit();
            return countryLanguages;
        }
    }

    public CountryLanguage getRandomItem(){
        CountryLanguage countryLanguage = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            countryLanguage = countryLanguageDao.getRandomItem();
            session.getTransaction().commit();
            return countryLanguage;
        }
    }

    private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        CountryLanguageService countryLanguageService = new CountryLanguageService();

        CountryLanguage countryLanguage = countryLanguageService.getRandomItem();
        System.out.println(countryLanguage.getLanguage());
    }
}
