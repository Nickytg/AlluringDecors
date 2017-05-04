/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lehai
 */

import java.io.*;
import java.util.*;

/**
 * Co the xem nhu la mot lop DAO, quan ly viec them sua xoa cua cac doi tuong du lieu entity.
 * Su dung mot lop nay cho tat ca cac loai entity. Neu can co the custom sau.
 * @author lehai
 * @param <T> 
 */
@SuppressWarnings("unchecked")
public class AbstractDAO<T> {

    private Class<T> entityClass;
   protected final

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractDAO() {
    }

    public List<T> getAll() {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            return sessionFactory.getCurrentSession()
                    .createQuery("from " + entityClass.getName()).list();
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
            return null;
        }
    }

    
    
    public void update(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().merge(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public void delete(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().delete(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public void insert(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().save(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public T find(Object primarykey) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            return (T) sessionFactory.getCurrentSession().get(entityClass,
                    (Integer) primarykey);
        } catch (RuntimeException re) {
            return null;
        }
    }
}
