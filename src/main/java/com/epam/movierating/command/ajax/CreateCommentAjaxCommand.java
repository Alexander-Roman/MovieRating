package com.epam.movierating.command.ajax;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.view.CommentView;
import com.epam.movierating.view.json.adapter.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateCommentAjaxCommand implements AjaxCommand {

    private final CommentService commentService;

    public CreateCommentAjaxCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);

        String movieIdParameter = request.getParameter(Parameter.ID);
        long movieId = Long.parseLong(movieIdParameter);

        String text = request.getParameter(Parameter.TEXT);

        LocalDateTime dateTime = LocalDateTime.now();

        CommentDto commentDto = new CommentDto(null, movieId, account, dateTime, text);
        commentService.createNewComment(commentDto);

        List<CommentDto> comments = commentService.getMovieComments(movieId);
        List<CommentView> commentViews = comments
                .stream()
                .map(CommentView::from)
                .collect(Collectors.toList());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String json = gson.toJson(commentViews);
        return Optional.of(json);
    }
}