package com.epam.movierating.filter;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessControlFilter implements Filter {

    private static final Role DEFAULT_ROLE = Role.GUEST;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Role role;
        if (account == null) {
            role = DEFAULT_ROLE;
        } else {
            role = account.getRole();
        }

        String command = request.getParameter(Parameter.COMMAND);
        if (role.hasAccess(command)) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Page.LOGIN);
            requestDispatcher.forward(request, response);
        }
    }
}
