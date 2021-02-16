package ua.mainacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.User;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class ItemDAO extends BaseDAO<Item>{

    public List<Item> findAll() {
        SessionFactory sessionFactory = super.getPostgresSessionFactory().getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        String sql = "" +
                "SELECT * " +
                "FROM items";
        Query query = session.createNativeQuery(sql, Item.class);
        List<Item> result = query.getResultList();
        session.close();
        return result;
    }
}
