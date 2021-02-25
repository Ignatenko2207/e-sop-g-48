package ua.mainacademy.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.mainacademy.dao.OrderDAO;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OpenOrderControllerTest {

    private static final Logger LOG = Logger.getLogger(OpenOrderControllerTest.class.getName());
    @Test
    void doGet() {
        UserDAO userDAO = new UserDAO();
        User user = User.builder()
                .login("test_login")
                .password("test_pass")
                .firstName("f_name")
                .lastName("l_name")
                .phone("+380505555555")
                .email("my.email@mail.com")
                .build();
        User savedUser = userDAO.save(user);

        HttpServletRequest req = Mockito.spy(HttpServletRequest.class);
        HttpServletResponse res = Mockito.spy(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = Mockito.spy(RequestDispatcher.class);
        when(req.getParameter(anyString())).thenReturn(savedUser.getId().toString());
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        OpenOrderController controller = new OpenOrderController();

        OrderDAO orderDAO = new OrderDAO();
        Order order;
        try {
            order = orderDAO.finddOpenOrderByUserId(savedUser.getId());
            if (nonNull(order)) {
                fail("Order for user exists");
            }
        } catch (Exception e) {
            LOG.info("It's OK! Order was found for new user");
        }

        try {
            controller.doGet(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method was processed with exception");
        }

//        Cookie[] cockies = req.getCookies();
//
//        Cookie filteredCookie =
//                Arrays.stream(cockies)
//                        .filter(it -> it.getName().equals("x-auth"))
//                        .findFirst().orElse(null);
//        assertNotNull(filteredCookie);

        try {
            order = orderDAO.finddOpenOrderByUserId(savedUser.getId());
            assertNotNull(order);
            orderDAO.delete(order);
        } catch (Exception e) {
            fail("It's not OK! Open order was not found for user");
        }
        userDAO.delete(savedUser);
    }
}