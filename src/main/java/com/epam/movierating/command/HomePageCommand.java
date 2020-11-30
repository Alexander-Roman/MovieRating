package com.epam.movierating.command;

import com.epam.movierating.constant.Page;
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
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        List<Movie> movies = movieService.getAll();
        request.setAttribute(MOVIES_ATTRIBUTE, movies);
        return CommandResult.forward(Page.HOME);
    }
}
