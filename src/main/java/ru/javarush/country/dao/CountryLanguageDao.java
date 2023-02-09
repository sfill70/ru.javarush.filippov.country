package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.CountryLanguage;

public class CountryLanguageDao extends GenericDao<CountryLanguage> {

    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }


}
