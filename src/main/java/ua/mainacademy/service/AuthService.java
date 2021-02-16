package ua.mainacademy.service;

import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;

import java.util.Optional;

public class AuthService {

    public Optional<User> findOneByLoginAndPassword(String login, String password) {
        UserDAO userDAO = new UserDAO();
        return userDAO.findOneByLoginAndPassword(login, password);
    }

}
