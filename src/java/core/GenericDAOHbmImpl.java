/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Hai Nguyen
 * @param <T>
 * @param <PK>
 */
public class GenericDAOHbmImpl<T, PK extends Serializable> implements GenericDAOHbm<T, PK> {

    protected static final Logger LOG = Logger.getLogger(GenericDAOHbmImpl.class.getName());
    protected SessionFactory sf = HibernateUtil.getSessionFactory();
    protected Class<T> entityClass;

    public GenericDAOHbmImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    public GenericDAOHbmImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T t) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        try {
            PK pk = (PK) s.save(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            s.close();
        }
    }

    @Override
    public T read(PK pk) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        try {
            T t = (T) s.createCriteria(entityClass, (String) pk);
            return t;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<T> read() {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        try {
            return s.createCriteria(entityClass).list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public boolean update(T t) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        try {
            s.update(t);
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            s.close();
        }
    }

    @Override
    public boolean delete(T t) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        try {
            s.delete(t);
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            s.close();
        }
    }

}
