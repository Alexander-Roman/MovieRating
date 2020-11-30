package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.LocalizationService;
import com.epam.movierating.logic.LocalizationServiceImpl;
import com.epam.movierating.view.localization.LocalizationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {

    private static final String REFERER_HEADER = "Referer";
    private final LocalizationService localizationService;

    public LocaleCommand() {
        localizationService = new LocalizationServiceImpl();
    }

    public LocaleCommand(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        LocalizationManager localization = localizationService.getLocalizationManager(language);
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