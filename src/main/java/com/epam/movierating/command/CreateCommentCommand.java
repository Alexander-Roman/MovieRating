package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class CreateCommentCommand implements Command {

    private static final String COMMAND_MOVIE = "/controller?command=movie&id=";
    private final CommentService commentService;

    public CreateCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        if (account == null) {
            throw new ServiceException("Comment author account undefined!");
        }

        String movieIdParameter = request.getParameter(Parameter.ID);
        long movieId = Long.parseLong(movieIdParameter);

        String text = request.getParameter(Parameter.TEXT);
        if (text == null || text.isEmpty()) {
            throw new ServiceException("No comment text included!");
        }

        LocalDateTime dateTime = LocalDateTime.now();

        CommentDto commentDto = new CommentDto(null, movieId, account, dateTime, text);
        commentService.createNewComment(commentDto);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + COMMAND_MOVIE + movieId);
    }
}
