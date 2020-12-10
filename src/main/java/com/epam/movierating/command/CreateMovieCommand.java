package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CreateMovieCommand implements Command {

    private final MovieService movieService;

    public CreateMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        if (idParameter != null) {
            Long movieId = Long.parseLong(idParameter);
            Optional<Movie> found = movieService.getById(idParameter);
            if (found.isPresent()) {
                Movie movie = found.get();
                request.setAttribute(Attribute.MOVIE, movie);
            }
        }

        return CommandResult.forward(Page.MOVIE_EDITOR);
    }
}
