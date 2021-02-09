package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.UserRatingService;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class MovieCommand implements Command {

    private final MovieService movieService;
    private final UserRatingService userRatingService;
    private final CommentService commentService;

    public MovieCommand(MovieService movieService, UserRatingService userRatingService, CommentService commentService) {
        this.movieService = movieService;
        this.userRatingService = userRatingService;
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);


        Movie movie = movieService.getById(id);
        request.setAttribute(Attribute.MOVIE, movie);


        List<CommentDto> comments = commentService.getMovieComments(id);
        request.setAttribute(Attribute.COMMENTS, comments);


        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Optional<Integer> personalRateFound = Optional.empty();
        if (account != null) {
            long accountId = account.getId();
            personalRateFound = userRatingService.getPersonalAssessment(id, accountId);
        }
        if (personalRateFound.isPresent()) {
            int personalRate = personalRateFound.get();
            request.setAttribute(Attribute.PERSONAL_RATE, personalRate);
        }

        return CommandResult.forward(Page.MOVIE);
    }
}
