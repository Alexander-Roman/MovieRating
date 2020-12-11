package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.PageNotFoundException;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomeCommand implements Command {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;
    private final MovieService movieService;

    public HomeCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String pageParameter = request.getParameter(Parameter.PAGE);
        int page;
        if (pageParameter == null) {
            page = DEFAULT_PAGE;
        } else {
            page = Integer.parseInt(pageParameter);
        }

        int numberOfPages = movieService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new PageNotFoundException();
        }

        List<Movie> movies = movieService.getPage(page, DEFAULT_ITEMS_PER_PAGE);

        request.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        request.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Attribute.PAGE, page);
        request.setAttribute(Attribute.MOVIES, movies);

        return CommandResult.forward(Page.HOME);
    }
}
