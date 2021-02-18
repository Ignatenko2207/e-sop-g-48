package ua.mainacademy.service;

import ua.mainacademy.dao.OrderDAO;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;

import java.util.Date;
import java.util.logging.Logger;

public class OrderService {

    private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

    public Order findOpenOrderOrCreateNew(User user) {
        OrderDAO orderDAO = new OrderDAO();
        try {
            return orderDAO.finddOpenOrderByUserId(user.getId());
        } catch (Exception e) {
            LOG.severe("There is no open order for user with id " + user.getId());
        }
        return orderDAO.save(
                new Order(user, new Date().getTime(), Order.Status.OPEN)
        );
    }
}
