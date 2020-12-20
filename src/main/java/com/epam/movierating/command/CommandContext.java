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

public class CommandContext {

    private final DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();

    private final Validator<Movie> movieValidator = new MovieValidator();
    private final Validator<CommentDto> commentDtoValidator = new CommentDtoValidator();
    private final Validator<UserRatingDto> userRatingDtoValidator = new UserRatingDtoValidator();

    private final MovieService movieService = new MovieServiceImpl(factory, movieValidator);
    private final AccountService accountService = new AccountServiceImpl(factory);
    private final CommentService commentService = new CommentServiceImpl(factory, commentDtoValidator);
    private final UserRatingService userRatingService = new UserRatingServiceImpl(factory, userRatingDtoValidator);

    private final Command homeCommand = new HomeCommand(movieService);
    private final Command movieCommand = new MovieCommand(movieService, userRatingService, commentService);
    private final Command editMovieCommand = new EditMovieCommand(movieService);
    private final Command createMovieCommand = new PageForwardCommand(Page.MOVIE_EDITOR);
    private final Command saveMovieCommand = new SaveMovieCommand(movieService);
    private final Command deleteMovieCommand = new DeleteMovieCommand(movieService);
    private final Command localizationCommand = new LocalizationCommand();
    private final Command loginPageCommand = new PageForwardCommand(Page.LOGIN);
    private final Command loginCommand = new LoginCommand(accountService);
    private final Command logoutCommand = new LogoutCommand();
    private final Command userListCommand = new UserListCommand(accountService);
    private final Command blockUserCommand = new BlockUserCommand(accountService);
    private final Command unblockUserCommand = new UnblockUserCommand(accountService);
    private final Command blockEditorCommand = new BlockEditorCommand(accountService);
    private final Command unblockEditorCommand = new UnblockEditorCommand(accountService);
    private final Command promoteUserCommand = new PromoteUserCommand(accountService);
    private final Command demoteEditorCommand = new DemoteEditorCommand(accountService);
    private final Command rateMovieCommand = new RateMovieCommand(userRatingService);
    private final Command createCommentCommand = new CreateCommentCommand(commentService);
    private final Command deleteCommentCommand = new DeleteCommentCommand(commentService);

    public Command getByCommandName(String commandName) {
        if (commandName == null) {
            return homeCommand;
        }

        switch (commandName) {
            case CommandName.HOME:
                return homeCommand;
            case CommandName.MOVIE:
                return movieCommand;
            case CommandName.EDIT_MOVIE:
                return editMovieCommand;
            case CommandName.CREATE_MOVIE:
                return createMovieCommand;
            case CommandName.SAVE_MOVIE:
                return saveMovieCommand;
            case CommandName.DELETE_MOVIE:
                return deleteMovieCommand;
            case CommandName.LOCALIZATION:
                return localizationCommand;
            case CommandName.LOGIN_PAGE:
                return loginPageCommand;
            case CommandName.LOGIN:
                return loginCommand;
            case CommandName.LOGOUT:
                return logoutCommand;
            case CommandName.USER_LIST:
                return userListCommand;
            case CommandName.BLOCK_USER:
                return blockUserCommand;
            case CommandName.UNBLOCK_USER:
                return unblockUserCommand;
            case CommandName.BLOCK_EDITOR:
                return blockEditorCommand;
            case CommandName.UNBLOCK_EDITOR:
                return unblockEditorCommand;
            case CommandName.PROMOTE_USER:
                return promoteUserCommand;
            case CommandName.DEMOTE_EDITOR:
                return demoteEditorCommand;
            case CommandName.RATE_MOVIE:
                return rateMovieCommand;
            case CommandName.CREATE_COMMENT:
                return createCommentCommand;
            case CommandName.DELETE_COMMENT:
                return deleteCommentCommand;
            default:
                throw new IllegalArgumentException(commandName + " is unknown command!");
        }
    }
}
