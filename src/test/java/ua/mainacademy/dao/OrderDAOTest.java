package ua.mainacademy.dao;

import org.junit.jupiter.api.Test;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    User user;
    Order order;

    @Test
    void finddOpenOrderByUserId() {
        user = User.builder()
                .login("test_login")
                .password("test_pass")
                .firstName("f_name")
                .lastName("l_name")
                .phone("+380505555555")
                .email("my.email@mail.com")
                .build();
        UserDAO userDAO = new UserDAO();
        User savedUser = userDAO.save(user);

        OrderDAO orderDAO = new OrderDAO();
        order = Order.builder()
                .creationTime(new Date().getTime())
                .user(user)
                .status(Order.Status.OPEN)
                .build();

        Order savedOrder = orderDAO.save(order);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        Order expected = orderDAO.finddOpenOrderByUserId(savedUser.getId());
        assertNotNull(expected);
        assertNotNull(expected.getId());
        assertEquals(expected.getId(), savedOrder.getId());

        orderDAO.delete(order);
        userDAO.delete(user);
    }
}