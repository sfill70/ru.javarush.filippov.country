package ru.javarush.country.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.Country;

import java.util.List;

public class CountryDao extends GenericDao<Country> {

    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    public List<Country> getAll() {
        Query<Country> query = getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class);
        return query.list();
    }
}
