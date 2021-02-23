package ua.mainacademy.service;

import lombok.RequiredArgsConstructor;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    public Optional<User> save(User user) {
        UserDAO userDAO = new UserDAO();
        if (nonNull(user.getId())) {
            throw new RuntimeException("Creation is failed!");
        }
        if (findAllByLogin(user.getLogin()).isEmpty()) {
            return Optional.of(userDAO.save(user));
        }
        return Optional.empty();
    }

    public Optional<User> update(User user) {
        UserDAO userDAO = new UserDAO();
        if (isNull(user.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        if (findAllByLogin(user.getLogin()).isEmpty()) {
            return Optional.of(userDAO.save(user));
        }
        return Optional.empty();
    }

    public Optional<User> findOneByLoginAndPassword(String login, String password) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.findOneByLoginAndPassword(login, password);
        } catch (Exception e) {
            LOG.severe(String.format("User with login %s and password %s was not found", login, password));
        }
        return Optional.empty();
    }

    public List<User> findAllByLogin(String login) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.findAllByLogin(login);
        } catch (Exception e) {
            LOG.severe(String.format("Any user with login %s was not found", login));
        }
        return new ArrayList<>();
    }

    public Optional<User> findById(int userId) {
        UserDAO userDAO = new UserDAO();
        try {
            return Optional.of(userDAO.findById(userId));
        } catch (Exception e) {
            LOG.severe(String.format("User with id %d was not found", userId));
        }
        return Optional.empty();
    }
}
