package ua.mainacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.mainacademy.factory.impl.PostgresSessionFactory;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.OrderItem;
import ua.mainacademy.model.User;

import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.isNull;

public class OrderItemDAO extends BaseDAO<OrderItem>{

}
