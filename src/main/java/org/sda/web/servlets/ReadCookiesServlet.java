package org.sda.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadCookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //pobieranie ciasteczek z zapytania
        Cookie[] cookies = req.getCookies();

        ServletOutputStream outputStream = resp.getOutputStream();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cookie".equals(cookie.getName())) {
                    outputStream.println("<h1>Cookie found, value = " + cookie.getValue() + "</h1>");
                }
            }
        } else {
            outputStream.println("<h1>Cookie not found</h1>");
        }
    }
}
