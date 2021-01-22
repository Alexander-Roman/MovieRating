package com.epam.movierating.command.ajax;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteCommentAjaxCommand implements AjaxCommand {

    private final CommentService commentService;

    public DeleteCommentAjaxCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) throws ServiceException {
        String commentIdParameter = request.getParameter(Parameter.ID);
        long commentId = Long.parseLong(commentIdParameter);

        commentService.deleteCommentById(commentId);

        return Optional.empty();
    }
}
