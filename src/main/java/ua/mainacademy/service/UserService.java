package ua.mainacademy.service;

import lombok.RequiredArgsConstructor;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public User save(User user) {
        if (nonNull(user.getId())) {
            throw new RuntimeException("Creation is failed!");
        }
        return userDAO.save(user);
    }

    public User update(User user) {
        if (isNull(user.getId())) {
            throw new RuntimeException("Creation is failed!");
        }
        return userDAO.save(user);
    }
}
