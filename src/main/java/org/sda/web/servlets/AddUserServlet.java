package org.sda.web.servlets;

import org.sda.web.global.UsersListSingletonEnum;
import org.sda.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String admin = (String) session.getAttribute("user");
            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            User user = new User(firstName, lastName);
            UsersListSingletonEnum singleton = UsersListSingletonEnum.INSTANCE;
            singleton.addUser(admin, user);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

