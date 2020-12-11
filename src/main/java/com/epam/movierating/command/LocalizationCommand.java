package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements Command {

    private static final String REFERER_HEADER = "Referer";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        Localization localization = Localization.valueOf(language);

        HttpSession session = request.getSession();
        session.setAttribute(Attribute.LOCALIZATION, localization);

        String page = request.getHeader(REFERER_HEADER);
        if (page == null) {
            page = request.getContextPath();
            return CommandResult.redirect(page);
        }
        return CommandResult.redirect(page);
    }
}