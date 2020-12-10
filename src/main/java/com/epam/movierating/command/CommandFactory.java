package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Page;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.*;
import com.epam.movierating.logic.validator.LocalizationParameterValidator;
import com.epam.movierating.logic.validator.PageParameterValidator;

public class CommandFactory {

    public static Command create(String command) {
        if (command == null) {
            DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
            MovieService movieService = new MovieServiceImpl(factory);
            return new HomeCommand(movieService);
        }

        switch (command) {
            case CommandName.LOGIN:
                return new LoginCommand(new AccountServiceImpl(new DaoConnectionManagerFactory()));
            case CommandName.MOVIE:
                return new MovieCommand(new MovieServiceImpl(new DaoConnectionManagerFactory()), new UserRatingServiceImpl(new DaoConnectionManagerFactory()), new CommentServiceImpl(new DaoConnectionManagerFactory()));
            case CommandName.LANGUAGE:
                return new LanguageCommand(new LocalizationParameterValidator());
            case CommandName.LOGIN_PAGE:
                return new PageForwardCommand(Page.LOGIN);
            case CommandName.CREATE_MOVIE:
                return new CreateMovieCommand(new MovieServiceImpl(new DaoConnectionManagerFactory()));
            case CommandName.SAVE_MOVIE:
                return new SaveMovieCommand(new MovieServiceImpl(new DaoConnectionManagerFactory()));
            case CommandName.USER_LIST:
                return new UserListCommand(new AccountServiceImpl(new DaoConnectionManagerFactory()));
            default:
                return new HomeCommand(new MovieServiceImpl(new DaoConnectionManagerFactory()));
        }
    }
}
