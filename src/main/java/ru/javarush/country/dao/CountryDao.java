package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.Country;

public class CountryDao extends GenericDao<Country>{

        public CountryDao(SessionFactory sessionFactory) {
            super(Country.class, sessionFactory);
        }
}
