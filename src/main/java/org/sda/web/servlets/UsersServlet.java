package org.sda.web.servlets;

import org.sda.web.global.UsersListSingletonEnum;
import org.sda.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class UsersServlet extends HttpServlet {
    public void init() {
        String filename = getServletContext().getRealPath("WEB-INF/classes/users.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            reader.lines()
                    .map(line -> line.split(","))
                    .map(elements -> new User(elements[0], elements[1]))
                    .forEach(UsersListSingletonEnum.INSTANCE::addUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<ul>");
        UsersListSingletonEnum.INSTANCE.getUsers()
                .forEach(line -> writer.println("<li>" + line + "</li>"));
        writer.println("</ul>");
    }

    public void destroy() {
        String filename = getServletContext().getRealPath("WEB-INF/classes/users.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
            UsersListSingletonEnum.INSTANCE.getUsers().stream()
                    .map(user -> user.getFirstName() + "," + user.getLastName())
                    .forEach(user -> {
                        try {
                            writer.write(user);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
