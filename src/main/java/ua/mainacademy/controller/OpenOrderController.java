package ua.mainacademy.controller;

import ua.mainacademy.controller.dto.OrderItemDTO;
import ua.mainacademy.controller.mapper.OrderItemMapper;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;
import ua.mainacademy.service.OrderItemService;
import ua.mainacademy.service.OrderService;
import ua.mainacademy.service.UserService;
import ua.mainacademy.util.Base64Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@WebServlet(urlPatterns = "/open-order")
public class OpenOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store");

        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        OrderItemService orderItemService = new OrderItemService();

        String userId = req.getParameter("userId");

        Optional<User> optUser = Optional.ofNullable(userService.findById(Integer.parseInt(userId)))
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s was not found", userId)));
        Order order = orderService.findOpenOrderOrCreateNew(optUser.get());
        List<OrderItemDTO> orderItemDTOList = orderItemService.findAllOrderItemByOrder(order)
                .stream()
                .map(OrderItemMapper::toOrderItemDTO)
                .collect(Collectors.toList());

        req.setAttribute("userId", userId);
        req.setAttribute("userName", optUser.get().getFirstName());
        req.setAttribute("userSurname", optUser.get().getLastName());
        req.setAttribute("message", getMessage(orderItemDTOList));
        req.setAttribute("orderItems", orderItemDTOList);
        req.setAttribute("orderId", order.getId());
        resp.addCookie(new Cookie("x-auth", Base64Util.getEncodedUserData(optUser.get())));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/open-order.jsp");
        dispatcher.forward(req, resp);
    }

    private String getMessage(List<OrderItemDTO> orderItemDTOList) {
        if (isNull(orderItemDTOList) || orderItemDTOList.isEmpty()) {
            return "You have 0 items in cart.";
        }
        return String.format("You have %d items in cart.", orderItemDTOList.size());
    }

}
