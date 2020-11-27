package com.epam.movierating.command;

import com.epam.movierating.entity.CommandResult;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.MovieServiceImpl;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomePageCommand implements Command {

    private static final String MOVIES_ATTRIBUTE = "movies";
    private final MovieService movieService = new MovieServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        List<Movie> movies;
        try {
            movies = movieService.getAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(MOVIES_ATTRIBUTE, movies);
        return CommandResult.forward(Page.HOME);
    }
}
