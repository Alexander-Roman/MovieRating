package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleCommand implements Command {

    private static final String REFERER_HEADER = "Referer";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        String country = request.getParameter(Parameter.COUNTRY);
        HttpSession session = request.getSession();

        if (language != null && country != null) {
            Locale locale = new Locale(language, country);
            session.setAttribute(Attribute.LOCALE, locale);
        }

        String page = request.getHeader(REFERER_HEADER);
        if (page == null) {
            page = request.getContextPath();
            return CommandResult.redirect(page);
        }
        return CommandResult.redirect(page);
    }
}