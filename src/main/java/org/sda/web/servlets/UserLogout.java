package org.sda.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLogout", urlPatterns = {"/adminLogout"})
public class UserLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("SESSION_ID".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
            if ("TOKEN".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }
}
