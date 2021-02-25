package ua.mainacademy.controller;

import ua.mainacademy.controller.dto.ItemDTO;
import ua.mainacademy.controller.mapper.ItemMapper;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.User;
import ua.mainacademy.service.ItemService;
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

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store");

        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .firstName(req.getParameter("fname"))
                .lastName(req.getParameter("lname"))
                .email(req.getParameter("email"))
                .phone(req.getParameter("phone"))
                .build();

        RequestDispatcher dispatcher;
        UserService userService = new UserService();
        ItemService itemService = new ItemService();
        Optional<User> optUser = userService.save(user);
        if (optUser.isPresent()) {
            User savedUser = optUser.get();
            req.setAttribute("userId", savedUser.getId());
            req.setAttribute("userName", savedUser.getFirstName());
            req.setAttribute("userSurname", savedUser.getLastName());
            req.setAttribute("items", toItemDTOList(itemService.findAll()));
            resp.addCookie(new Cookie("x-auth", Base64Util.getEncodedUserData(optUser.get())));
            dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "User with such login is present. Try other login please");
            dispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private List<ItemDTO> toItemDTOList(List<Item> items) {
        return items.stream()
                .map(ItemMapper::toItemDTO)
                .collect(Collectors.toList());
    }
}
