package com.epam.movierating.command;

import com.epam.movierating.command.context.CommandResult;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MovieCommand implements Command {

    private final MovieService movieService;

    public MovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String id = request.getParameter(Parameter.MOVIE);
        Optional<Movie> result = movieService.getById(id);
        if (result.isPresent()) {
            Movie movie = result.get();
            request.setAttribute("movie", movie);
            return CommandResult.forward(Page.MOVIE_EDITOR);
        } else {
            return CommandResult.redirect(Page.INDEX);
        }
    }
}
