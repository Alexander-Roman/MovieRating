package com.epam.movierating.command;

import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommentCommand implements Command {

    private static final String REFERER_HEADER = "Referer";
    private final CommentService commentService;

    public DeleteCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String commentIdParameter = request.getParameter(Parameter.ID);
        long commentId = Long.parseLong(commentIdParameter);

        commentService.deleteCommentById(commentId);

        String page = request.getHeader(REFERER_HEADER);
        if (page == null) {
            return CommandResult.redirect(Page.INDEX);
        }
        return CommandResult.redirect(page);
    }
}
