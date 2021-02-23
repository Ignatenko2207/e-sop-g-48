package ua.mainacademy.util;

import ua.mainacademy.model.User;

import java.util.Base64;

public class Base64Util {

    public static String getEncodedUserData(User user) {
        return Base64.getEncoder().encodeToString(String.format("%s:%s", user.getLogin(), user.getPassword()).getBytes());
    }

    public static String getDecodedString(String text) {
        return new String(Base64.getDecoder().decode(text)); //login:password
    }
}
