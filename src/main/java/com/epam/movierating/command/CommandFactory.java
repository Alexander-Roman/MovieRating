package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Page;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.*;

public class CommandFactory {

    public static Command create(String command) {
        if (command == null) {
            DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
            MovieService movieService = new MovieServiceImpl(factory);
            return new HomeCommand(movieService);
        }

        switch (command) {
            case CommandName.HOME: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                MovieService movieService = new MovieServiceImpl(factory);
                return new HomeCommand(movieService);
            }
            case CommandName.MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                MovieService movieService = new MovieServiceImpl(factory);
                UserRatingService userRatingService = new UserRatingServiceImpl(factory);
                CommentService commentService = new CommentServiceImpl(factory);
                return new MovieCommand(movieService, userRatingService, commentService);
            }
            case CommandName.EDIT_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                MovieService movieService = new MovieServiceImpl(factory);
                return new EditMovieCommand(movieService);
            }
            case CommandName.CREATE_MOVIE: {
                return new PageForwardCommand(Page.MOVIE_EDITOR);
            }
            case CommandName.SAVE_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                MovieService movieService = new MovieServiceImpl(factory);
                return new SaveMovieCommand(movieService);
            }
            case CommandName.LOGIN_PAGE: {
                return new PageForwardCommand(Page.LOGIN);
            }
            case CommandName.LOGIN: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new LoginCommand(accountService);
            }
            case CommandName.LOCALIZATION: {
                return new LocalizationCommand();
            }
            case CommandName.USER_LIST: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new UserListCommand(accountService);
            }
            default: {
                throw new IllegalArgumentException("Command unknown!");
            }
        }
    }
}
