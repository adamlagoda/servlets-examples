package org.sda.web.servlets;

import org.sda.web.database.dao.AdminsDao;
import org.sda.web.database.dao.SessionsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class UserLogin extends HttpServlet {

    private Random generator;
    private AdminsDao adminsDao;
    private SessionsDao sessionsDao;

    public void init() {
        generator = new Random();
        adminsDao = new AdminsDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean found = adminsDao.hasAdmin(login, password);

        if (found) {
            long token = generator.nextLong();
            long sessionId = generator.nextLong();
            sessionsDao.save(sessionId, token);
            resp.addHeader("SESSION_ID", String.valueOf(sessionId));
            resp.addHeader("TOKEN", String.valueOf(token));
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
