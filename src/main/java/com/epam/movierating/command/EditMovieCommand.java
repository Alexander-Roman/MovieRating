package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EditMovieCommand implements Command {

    private final MovieService movieService;

    public EditMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        Optional<Movie> found = movieService.getById(id);
        if (found.isPresent()) {
            Movie movie = found.get();
            request.setAttribute(Attribute.MOVIE, movie);
        } else {
            throw new ServiceException("Requested movie is not present!");
        }

        return CommandResult.forward(Page.MOVIE_EDITOR);
    }
}
