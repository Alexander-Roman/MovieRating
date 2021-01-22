package com.epam.movierating.filter;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessControlFilter implements Filter {

    private static final Role DEFAULT_ROLE = Role.GUEST;
    private static final int UNAUTHORIZED_ERROR_CODE = 401;
    private static final int FORBIDDEN_ERROR_CODE = 403;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Role role = account == null
                ? DEFAULT_ROLE
                : account.getRole();

        String command = request.getParameter(Parameter.COMMAND);
        if (command == null || role.hasAccess(command)) {
            chain.doFilter(request, response);
        } else if (account == null) {
            ((HttpServletResponse) response).sendError(UNAUTHORIZED_ERROR_CODE);
        } else {
            ((HttpServletResponse) response).sendError(FORBIDDEN_ERROR_CODE);
        }
    }

    @Override
    public void destroy() {

    }
}
