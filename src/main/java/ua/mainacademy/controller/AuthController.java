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

@WebServlet(urlPatterns = "/authorization")
public class AuthController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store");

        UserService userService = new UserService();
        ItemService itemService = new ItemService();

        RequestDispatcher dispatcher;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> optUser = userService.findOneByLoginAndPassword(login, password);

        if (optUser.isPresent()) {
            req.setAttribute("userId", optUser.get().getId());
            req.setAttribute("userName", optUser.get().getFirstName());
            req.setAttribute("userSurname", optUser.get().getLastName());
            req.setAttribute("items", toItemDTOList(itemService.findAll()));
            resp.addCookie(new Cookie("x-auth", Base64Util.getEncodedUserData(optUser.get())));
            dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Wrong login or password. Please try again.");
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
