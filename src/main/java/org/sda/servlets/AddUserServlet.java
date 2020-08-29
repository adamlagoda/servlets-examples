package org.sda.servlets;

import org.sda.servlets.global.UsersListSingletonEnum;
import org.sda.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            User user = new User(firstName, lastName);
            UsersListSingletonEnum singleton = UsersListSingletonEnum.INSTANCE;
            singleton.addUser(user);
            resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}

