package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Movie;
import com.epam.movierating.logic.*;

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

        Optional<Movie> movieFound = movieService.getById(id);
        Movie movie;
        if (movieFound.isPresent()) {
            movie = movieFound.get();
            request.setAttribute(Attribute.MOVIE, movie);
        } else {
            throw new PageNotFoundException("Movie not found!");
        }

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Optional<Integer> personalRateFound = Optional.empty();
        if (account != null) {
            personalRateFound = userRatingService.getPersonalAssessment(movie, account);
        }

        if (personalRateFound.isPresent()) {
            int personalRate = personalRateFound.get();
            request.setAttribute(Attribute.PERSONAL_RATE, personalRate);
        }

        List<CommentDto> comments = commentService.getMovieComments(movie);
        request.setAttribute(Attribute.COMMENTS, comments);

        return CommandResult.forward(Page.MOVIE);
    }
}
