package ru.javarush.country.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GenericDao<T> {

    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    private int count;

    public GenericDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
        count = 0;
    }

    public T getById(final int id) {
         return (T) getCurrentSession().get(clazz, id);
       /* Query<T> query = getCurrentSession().createQuery("select e from " + clazz.getName() + " e where e.id=" + id, clazz);
        T entity = query.uniqueResult();
        return entity;*/
    }


    public List<T> getItems(int offset, int count) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }


    public Set<T> getItemsRandom(int offset, int count) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        List<T> listEntity = query.getResultList();
        Collections.shuffle(listEntity);
        if (count >= listEntity.size()) {
            count = listEntity.size() - 1;
        }
        return new HashSet<>(listEntity.subList(offset, count));
    }

    public T getRandomItem() {
        if (count == 0) {
            Query<Long> query = getCurrentSession().createQuery("select count (*) from " + clazz.getName(), Long.class);
            count = query.uniqueResult().intValue();
        }
        T entity = (T) getById(ThreadLocalRandom.current().nextInt(1, count));
        return entity;
    }

    public int getTotalCount(){
        Query<Long> query = getCurrentSession().createQuery("select count (*) from " + clazz.getName(), Long.class);
       return query.uniqueResult().intValue();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
        count--;
    }

    public void deleteById(final int entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
