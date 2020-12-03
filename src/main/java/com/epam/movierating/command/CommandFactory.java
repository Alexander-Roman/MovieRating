package com.epam.movierating.command;

import com.epam.movierating.constant.Page;
import com.epam.movierating.logic.LoginServiceImpl;
import com.epam.movierating.logic.MovieServiceImpl;
import com.epam.movierating.logic.validator.LocalizationParameterValidator;
import com.epam.movierating.logic.validator.PageParameterValidator;

public class CommandFactory {

    public static Command create(String command) {
        if (command == null) {
            return new HomeCommand(new MovieServiceImpl(), new PageParameterValidator());
        }
        switch (command) {
            case Command.LOGIN:
                return new LoginCommand(new LoginServiceImpl());
            case Command.MOVIE:
                return new MovieCommand();
            case Command.LANGUAGE:
                return new LanguageCommand(new LocalizationParameterValidator());
            case Command.LOGIN_PAGE:
                return new PageForwardCommand(Page.LOGIN);
            default:
                return new HomeCommand(new MovieServiceImpl(), new PageParameterValidator());
        }
    }
}
