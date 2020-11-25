package com.epam.movierating.command;

import com.epam.movierating.entity.CommandResult;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomePageCommand implements Command {

    private static final String MOVIES_ATTRIBUTE = "movies";
    private final MovieService movieService = new MovieServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Movie> movies = movieService.getAll();
        request.setAttribute(MOVIES_ATTRIBUTE, movies);
        return CommandResult.forward(Page.HOME);
    }
}
