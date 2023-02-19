package ru.javarush.country.factory;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

public class MySessionFactory {
    private static MySessionFactory instance;
    private final SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(MySessionFactory.class);

    private MySessionFactory() {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(CountryLanguage.class)
                    .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            try {
                instance = new MySessionFactory();
            } catch (Exception e) {
                logger.error("\nFailed to connect to database MySql - " + e.getMessage() + "\n");
            }
        }
        return instance.sessionFactory;
    }
}
