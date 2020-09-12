package org.sda.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ReadSession", value = {"/readSession"})
public class ReadSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            String name = (String)session.getAttribute("name");
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.println("<h1>Attribute name has value" + name + "</h1>");
        }
    }
}
