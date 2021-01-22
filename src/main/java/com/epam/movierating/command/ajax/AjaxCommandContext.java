package com.epam.movierating.command.ajax;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.CommentServiceImpl;
import com.epam.movierating.logic.validator.CommentDtoValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.CommentDto;

public class AjaxCommandContext {

    private final DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
    private final Validator<CommentDto> commentDtoValidator = new CommentDtoValidator();
    private final CommentService commentService = new CommentServiceImpl(factory, commentDtoValidator);

    private final AjaxCommand deleteCommentAjaxCommand = new DeleteCommentAjaxCommand(commentService);
    private final AjaxCommand createCommentAjaxCommand = new CreateCommentAjaxCommand(commentService);

    public AjaxCommand getByCommandName(String commandName) {
        if (commandName == null) {
            throw new IllegalArgumentException("Command not defined!");
        }

        switch (commandName) {
            case CommandName.DELETE_COMMENT:
                return deleteCommentAjaxCommand;
            case CommandName.CREATE_COMMENT:
                return createCommentAjaxCommand;
            default:
                throw new IllegalArgumentException("Command unknown: " + commandName);
        }
    }
}
