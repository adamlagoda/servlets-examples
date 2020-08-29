package org.sda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        String filename = getServletContext().getRealPath("WEB-INF/classes/users.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.lines()
                    .forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
