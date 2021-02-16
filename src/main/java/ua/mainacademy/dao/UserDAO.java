package ua.mainacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.mainacademy.factory.impl.PostgresSessionFactory;
import ua.mainacademy.model.User;

import javax.persistence.Query;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserDAO extends BaseDAO<User>{

    public Optional<User> findOneByLoginAndPassword(String login, String password) {
        if (isBlank(login) || isBlank(password)) {
            return Optional.empty();
        }
        SessionFactory sessionFactory = super.getPostgresSessionFactory().getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        String sql = "" +
                "SELECT * " +
                "FROM users " +
                "WHERE login=?1 AND password=?2";
        Query query = session.createNativeQuery(sql, User.class);
        query.setParameter(1, login);
        query.setParameter(2, password);
        Optional result = Optional.of(query.getSingleResult());
        session.close();
        return result;
    }

}
