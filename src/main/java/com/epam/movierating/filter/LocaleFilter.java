package com.epam.movierating.filter;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.view.localization.LocalizationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        HttpSession session = ((HttpServletRequest) request).getSession();
        Locale currentLocale = (Locale) session.getAttribute(Attribute.LOCALE);
        if (currentLocale == null) {
            Locale newLocale = request.getLocale();
            session.setAttribute(Attribute.LOCALE, newLocale);
        }
        chain.doFilter(request, response);
         */
        HttpSession session = ((HttpServletRequest) request).getSession();
        LocalizationManager currentLocalization = (LocalizationManager) session.getAttribute(Attribute.LOCALIZATION);
        if (currentLocalization == null) {
            session.setAttribute(Attribute.LOCALIZATION, LocalizationManager.EN);
        }
        chain.doFilter(request, response);
    }
}
