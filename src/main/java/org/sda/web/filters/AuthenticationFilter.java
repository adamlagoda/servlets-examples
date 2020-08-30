package org.sda.web.filters;

import org.sda.web.database.dao.SessionsDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", servletNames = {"UsersServlet"})
public class AuthenticationFilter implements Filter {

    private SessionsDao sessionsDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionsDao = new SessionsDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            long sessionId = Long.parseLong(((HttpServletRequest)request).getHeader("SESSION_ID"));
            long token = Long.parseLong(((HttpServletRequest)request).getHeader("TOKEN"));
            if (sessionsDao.hasActiveSession(sessionId, token)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NumberFormatException e) {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
