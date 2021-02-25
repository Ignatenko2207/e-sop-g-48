package ua.mainacademy.controller.filter;

import org.apache.commons.lang3.StringUtils;
import ua.mainacademy.model.User;
import ua.mainacademy.service.UserService;
import ua.mainacademy.util.Base64Util;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;

@WebFilter(urlPatterns = {
        "/open-order",
        "/order-item",
        "/item"
})
public class UserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        Cookie[] cookies = req.getCookies();
        String xAuth = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("x-auth")) {
                xAuth = cookie.getValue();
            }
        }
        RequestDispatcher requestDispatcher;
        if (isNull(xAuth)) {
            req.setAttribute("message", "Authorization is failed! User data is missed.");
            requestDispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
            requestDispatcher.forward(req, res);
            return;
        }
        String userData[] = Base64Util.getDecodedString(xAuth).split(":");
        UserService userService = new UserService();
        Optional<User> user = userService.findOneByLoginAndPassword(userData[0], userData[1]);
        String userId = req.getParameter("userId");
        if (user.isPresent() && StringUtils.equals(user.get().getId().toString(), userId)) {
            super.doFilter(req, res, chain);
        } else {
            req.setAttribute("message", "Authorization is failed! User data is wrong.");
            requestDispatcher = req.getRequestDispatcher("/jsp/default-auth.jsp");
            requestDispatcher.forward(req, res);
        }
    }
}
