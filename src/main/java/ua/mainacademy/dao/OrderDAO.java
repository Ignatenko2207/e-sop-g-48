package ua.mainacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.Order;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class OrderDAO extends BaseDAO<Order>{

    public Order finddOpenOrderByUserId(Integer userId) {
        SessionFactory sessionFactory = super.getPostgresSessionFactory().getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        String sql = "" +
                "SELECT * " +
                "FROM orders " +
                "WHERE user_id=1? " +
                "AND status=2?";
        Query query = session.createNativeQuery(sql, Order.class);
        query.setParameter(1, userId);
        query.setParameter(2, Order.Status.OPEN);
        Order result = (Order) query.getSingleResult();
        session.close();
        return result;
    }
}
