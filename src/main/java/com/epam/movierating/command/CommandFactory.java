package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Page;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.*;
import com.epam.movierating.logic.validator.CommentDtoValidator;
import com.epam.movierating.logic.validator.MovieValidator;
import com.epam.movierating.logic.validator.UserRatingDtoValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.dto.UserRatingDto;
import com.epam.movierating.model.entity.Movie;

public class CommandFactory {

    public static Command create(String command) {
        if (command == null) {
            DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
            Validator<Movie> movieValidator = new MovieValidator();
            MovieService movieService = new MovieServiceImpl(factory, movieValidator);
            return new HomeCommand(movieService);
        }

        switch (command) {
            case CommandName.HOME: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<Movie> movieValidator = new MovieValidator();
                MovieService movieService = new MovieServiceImpl(factory, movieValidator);
                return new HomeCommand(movieService);
            }
            case CommandName.MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<Movie> movieValidator = new MovieValidator();
                MovieService movieService = new MovieServiceImpl(factory, movieValidator);
                Validator<UserRatingDto> userRatingDtoValidator = new UserRatingDtoValidator();
                UserRatingService userRatingService = new UserRatingServiceImpl(factory, userRatingDtoValidator);
                Validator<CommentDto> commentDtoValidator = new CommentDtoValidator();
                CommentService commentService = new CommentServiceImpl(factory, commentDtoValidator);
                return new MovieCommand(movieService, userRatingService, commentService);
            }
            case CommandName.EDIT_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<Movie> movieValidator = new MovieValidator();
                MovieService movieService = new MovieServiceImpl(factory, movieValidator);
                return new EditMovieCommand(movieService);
            }
            case CommandName.CREATE_MOVIE: {
                return new PageForwardCommand(Page.MOVIE_EDITOR);
            }
            case CommandName.SAVE_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<Movie> movieValidator = new MovieValidator();
                MovieService movieService = new MovieServiceImpl(factory, movieValidator);
                return new SaveMovieCommand(movieService);
            }
            case CommandName.DELETE_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<Movie> movieValidator = new MovieValidator();
                MovieService movieService = new MovieServiceImpl(factory, movieValidator);
                return new DeleteMovieCommand(movieService);
            }
            case CommandName.LOCALIZATION: {
                return new LocalizationCommand();
            }
            case CommandName.LOGIN_PAGE: {
                return new PageForwardCommand(Page.LOGIN);
            }
            case CommandName.LOGIN: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new LoginCommand(accountService);
            }
            case CommandName.LOGOUT: {
                return new LogoutCommand();
            }
            case CommandName.USER_LIST: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new UserListCommand(accountService);
            }
            case CommandName.BLOCK_USER: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new BlockUserCommand(accountService);
            }
            case CommandName.UNBLOCK_USER: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new UnblockUserCommand(accountService);
            }
            case CommandName.BLOCK_EDITOR: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new BlockEditorCommand(accountService);
            }
            case CommandName.UNBLOCK_EDITOR: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new UnblockEditorCommand(accountService);
            }
            case CommandName.PROMOTE_USER: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new PromoteUserCommand(accountService);
            }
            case CommandName.DEMOTE_EDITOR: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new DemoteEditorCommand(accountService);
            }
            case CommandName.RATE_MOVIE: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<UserRatingDto> userRatingDtoValidator = new UserRatingDtoValidator();
                UserRatingService userRatingService = new UserRatingServiceImpl(factory, userRatingDtoValidator);
                return new RateMovieCommand(userRatingService);
            }
            case CommandName.CREATE_COMMENT: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<CommentDto> commentDtoValidator = new CommentDtoValidator();
                CommentService commentService = new CommentServiceImpl(factory, commentDtoValidator);
                return new CreateCommentCommand(commentService);
            }
            case CommandName.DELETE_COMMENT: {
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                Validator<CommentDto> commentDtoValidator = new CommentDtoValidator();
                CommentService commentService = new CommentServiceImpl(factory, commentDtoValidator);
                return new DeleteCommentCommand(commentService);
            }
            default: {
                throw new IllegalArgumentException(command + " is unknown command!");
            }
        }
    }
}