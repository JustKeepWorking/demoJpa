/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Hai Nguyen
 * @param <T>
 * @param <PK>
 */
public class GenericDAOHbmImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {
    
    protected SessionFactory sf = HibernateUtil.getSessionFactory();
    
    @Override
    public T create(T t) {
        Session s = sf.openSession();
        Transaction tx =s.beginTransaction();
        s.save(t);
        tx.commit();
        s.close();
        return t;
    }

    @Override
    public T read(PK pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T update(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
