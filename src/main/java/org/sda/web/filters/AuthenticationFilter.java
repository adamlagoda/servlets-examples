package org.sda.web.filters;

import org.sda.web.database.dao.SessionsDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
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
            long token = 0;
            long sessionId = 0;
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("SESSION_ID".equals(cookie.getName())) {
                        sessionId = Long.parseLong(cookie.getValue());
                    }
                    if ("TOKEN".equals(cookie.getName())) {
                        token = Long.parseLong(cookie.getValue());
                    }
                }
            }
            if (sessionsDao.hasActiveSession(sessionId, token)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NumberFormatException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
