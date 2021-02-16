package ua.mainacademy.dao;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.mainacademy.factory.impl.PostgresSessionFactory;
import ua.mainacademy.model.BaseEntity;

import java.lang.reflect.ParameterizedType;

import static java.util.Objects.isNull;

@Getter
public class BaseDAO<T extends BaseEntity> {

    private Class<T> type;
    private PostgresSessionFactory postgresSessionFactory = new PostgresSessionFactory();

    public BaseDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];;
    }

    public T findById(Integer id) {
        if (isNull(id)) {
            throw new RuntimeException("Search is failed!");
        }
        SessionFactory sessionFactory = postgresSessionFactory.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T t = session.get(type, id);
        transaction.commit();
        session.close();
        return t;
    }


    public T save(T t) {
        SessionFactory sessionFactory = postgresSessionFactory.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(t);
        transaction.commit();
        t.setId(id);
        session.close();
        return t;
    }

    public T update(T t) {
        if (isNull(t.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        SessionFactory sessionFactory = postgresSessionFactory.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
        return t;
    }

    public void delete(T t) {
        if (isNull(t.getId())) {
            throw new RuntimeException("Delete is failed!");
        }
        SessionFactory sessionFactory = postgresSessionFactory.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

}
