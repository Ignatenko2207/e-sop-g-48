package ua.mainacademy.controller;

import org.apache.commons.lang3.StringUtils;
import ua.mainacademy.controller.dto.ItemDTO;
import ua.mainacademy.controller.mapper.ItemMapper;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.User;
import ua.mainacademy.service.ItemService;
import ua.mainacademy.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

        String action = req.getParameter("action");
        UserService userService = new UserService(new UserDAO());
        ItemService itemService = new ItemService();
        RequestDispatcher dispatcher;
        if (StringUtils.equalsIgnoreCase(action, "login")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Optional<User> optUser = userService.findOneByLoginAndPassword(login, password);
            if (optUser.isPresent()) {
                req.setAttribute("userId", optUser.get().getId());
                req.setAttribute("userName", optUser.get().getFirstName());
                req.setAttribute("userSurname", optUser.get().getLastName());
                req.setAttribute("items", toItemDTOList(itemService.findAll()));
                dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("message", "Wrong login or password. Please try again.");
                dispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (StringUtils.equalsIgnoreCase(action, "register")) {
            String login = req.getParameter("login");
            User user = User.builder()
                    .login(login)
                    .password(req.getParameter("password"))
                    .firstName(req.getParameter("fname"))
                    .lastName(req.getParameter("lname"))
                    .email(req.getParameter("email"))
                    .phone(req.getParameter("phone"))
                    .build();

            Optional<User> optUser = userService.save(user);
            if (optUser.isPresent()) {
                User savedUser = optUser.get();
                req.setAttribute("userId", savedUser.getId());
                req.setAttribute("userName", savedUser.getFirstName());
                req.setAttribute("userSurname", savedUser.getLastName());
                req.setAttribute("items", toItemDTOList(itemService.findAll()));
                dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("message", "User with such login is present. Try other login please");
                dispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    private List<ItemDTO> toItemDTOList(List<Item> items) {
        return items.stream()
                .map(ItemMapper::toItemDTO)
                .collect(Collectors.toList());
    }
}
