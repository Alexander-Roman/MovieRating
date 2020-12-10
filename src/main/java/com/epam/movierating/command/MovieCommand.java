package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.CommentTo;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.UserRatingService;

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
        Optional<Movie> movieFound = movieService.getById(idParameter);
        Movie movie = null;
        if (movieFound.isPresent()) {
            movie = movieFound.get();
            request.setAttribute(Attribute.MOVIE, movie);
        }
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Optional<Integer> personalRateFound = userRatingService.getPersonalAssessment(movie, account);
        if (personalRateFound.isPresent()) {
            int personalRate = personalRateFound.get();
            request.setAttribute(Attribute.PERSONAL_RATE, personalRate);
        }
        if (movie != null) {
            List<CommentTo> comments = commentService.getMovieComments(movie);
            request.setAttribute(Attribute.COMMENTS, comments);
        }

        return CommandResult.forward(Page.MOVIE);
    }
}
