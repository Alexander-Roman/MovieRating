package com.epam.movierating.filter;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.view.localization.Localization;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Localization currentLocalization = (Localization) session.getAttribute(Attribute.LOCALIZATION);
        if (currentLocalization == null) {
            session.setAttribute(Attribute.LOCALIZATION, Localization.EN);
        }
        chain.doFilter(request, response);
    }
}
