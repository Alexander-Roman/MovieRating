package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
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
            case CommandName.LOGIN:
                return new LoginCommand(new LoginServiceImpl());
            case CommandName.MOVIE:
                return new MovieCommand(new MovieServiceImpl());
            case CommandName.LANGUAGE:
                return new LanguageCommand(new LocalizationParameterValidator());
            case CommandName.LOGIN_PAGE:
                return new PageForwardCommand(Page.LOGIN);
            case CommandName.CREATE_MOVIE:
                return new CreateMovieCommand();
            case CommandName.SAVE_MOVIE:
                return new SaveMovieCommand(new MovieServiceImpl());
            default:
                return new HomeCommand(new MovieServiceImpl(), new PageParameterValidator());
        }
    }
}
