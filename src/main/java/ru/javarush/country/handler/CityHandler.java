package ru.javarush.country.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.factory.MySessionFactory;

import java.util.Set;

public class CityHandler {
    private final SessionFactory sessionFactory;
    private final CityDao cityDao;

    public CityHandler() {
        sessionFactory = MySessionFactory.getSessionFactory();
        cityDao = new CityDao(sessionFactory);
    }

    public City getRandomCity() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            City city = cityDao.getRandomItem();
            session.getTransaction().commit();
            return city;
        }
    }

    public Set<City> getRandomCitySet(int offset, int count) {
        Set<City> citySet;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            citySet = cityDao.getItemsRandom(offset, count);
            session.getTransaction().commit();
        }
        return citySet;

    }

    public static void main(String[] args) {
        CityHandler cityHandler = new CityHandler();
        System.out.println(cityHandler.getRandomCity().getName());

        for (City city : cityHandler.getRandomCitySet(2, 18)
        ) {
            System.out.println(city.getName());
        }
    }


}
