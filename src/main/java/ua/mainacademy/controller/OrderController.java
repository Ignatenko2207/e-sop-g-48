package ua.mainacademy.controller;

import org.apache.commons.lang3.StringUtils;
import ua.mainacademy.controller.dto.OrderItemDTO;
import ua.mainacademy.controller.mapper.OrderItemMapper;
import ua.mainacademy.dao.OrderDAO;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;
import ua.mainacademy.service.OrderItemService;
import ua.mainacademy.service.OrderService;
import ua.mainacademy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store");

        UserService userService = new UserService(new UserDAO());
        OrderService orderService = new OrderService();

        String userId = req.getParameter("userId");
        if (StringUtils.isNumeric(userId)) {
            Optional<User> optUser = userService.findById(Integer.parseInt(userId));
            if (optUser.isPresent()) {
                Order order = orderService.findOpenOrderOrCreateNew(optUser.get());
                OrderItemService orderItemService = new OrderItemService();
                List<OrderItemDTO> orderItemDTOList = orderItemService.findAllOrderItemByOrder(order)
                        .stream()
                        .map(OrderItemMapper::toOrderItemDTO)
                        .collect(Collectors.toList());

            }
        }

    }

}
