package org.sda.servlets;

import org.sda.servlets.global.UsersListSingletonEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class UsersServlet extends HttpServlet {

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
//        String filename = getServletContext().getRealPath("WEB-INF/classes/users.txt");
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
//            writer.println("<ul>");
//            reader.lines()
//                    .forEach(line -> writer.println("<li>" + line + "</li>"));
//            writer.println("</ul>");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        writer.println("<ul>");
        UsersListSingletonEnum.INSTANCE.getUsers()
                .forEach(line -> writer.println("<li>" + line + "</li>"));
        writer.println("</ul>");
    }

}
