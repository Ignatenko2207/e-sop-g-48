package ua.mainacademy.controller;

import org.apache.commons.lang3.StringUtils;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/authorization")
public class AuthController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        RequestDispatcher dispatcher = null;
        if (StringUtils.equalsIgnoreCase(action, "login")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Optional<User> optUser = userDAO.findOneByLoginAndPassword(login, password);
            if (optUser.isPresent()) {
                req.setAttribute("userId", optUser.get().getId());
                req.setAttribute("userName", optUser.get().getFirstName());
                req.setAttribute("userSurname", optUser.get().getLastName());
                req.setAttribute("items", itemDAO.findAll());
                dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                dispatcher.forward(req, resp);
                return;
            } else {
                req.setAttribute("message", "Wrong login or password. Please try again.");
                dispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }
        else if (StringUtils.equalsIgnoreCase(action, "register")) {
            String login = req.getParameter("login");
            List<User> users = userDAO.findAllByLogin(login);
            if(users.isEmpty()) {
                User user = User.builder()
                        .login(login)
                        .password(req.getParameter("password"))
                        .firstName(req.getParameter("fname"))
                        .lastName(req.getParameter("lname"))
                        .email(req.getParameter("email"))
                        .phone(req.getParameter("phone"))
                        .build();
                User savedUser = userDAO.save(user);
                req.setAttribute("userId", savedUser.getId());
                req.setAttribute("userName", savedUser.getFirstName());
                req.setAttribute("userSurname", savedUser.getLastName());
                req.setAttribute("items", itemDAO.findAll());
                dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            else {
                req.setAttribute("message", "User with such login is present. Try other login please/");
                dispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }

    }
}
