package ua.mainacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.mainacademy.model.Item;

import javax.persistence.Query;
import java.util.List;

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
