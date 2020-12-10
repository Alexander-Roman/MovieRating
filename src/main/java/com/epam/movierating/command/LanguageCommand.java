package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.validator.LocalizationParameterValidator;
import com.epam.movierating.view.localization.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    private static final String REFERER_HEADER = "Referer";
    private final LocalizationParameterValidator validator;

    public LanguageCommand(LocalizationParameterValidator validator) {
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        Localization localization = validator.validate(language);
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