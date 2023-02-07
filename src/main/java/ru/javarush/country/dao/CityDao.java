package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;

public class CityDao extends GenericDao<City>{

    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
