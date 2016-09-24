
package core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

public class GenericDAOJpaImpl<T, PK extends Serializable> implements GenericDAO<T,PK> {
//    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("demoJpaPU");
    @PersistenceUnit(name = "demoJpaPU")
    protected static EntityManagerFactory factory;
    
    protected  Class<T> entityClass;

    public GenericDAOJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    
    
    public GenericDAOJpaImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public T create(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
        return t;
    }

    @Override
    public T read(PK pk) {
        EntityManager entityManager = factory.createEntityManager();
        T t = entityManager.find(entityClass, pk);
        entityManager.close();
        return t;
    }

    @Override
    public List<T> read() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT s FROM " + entityClass.getAnnotation(Table.class).name() + " s";
        List<T> result = (List<T>) entityManager.createQuery(query);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
    
    @Override
    public T update(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        T result = entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void delete(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        t = entityManager.merge(t);
        entityManager.remove(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    

}
