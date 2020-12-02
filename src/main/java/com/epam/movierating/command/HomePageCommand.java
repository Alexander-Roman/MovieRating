package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.MovieServiceImpl;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.validator.PageValidator;
import com.epam.movierating.logic.validator.PageValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomePageCommand implements Command {

    private static final int ITEMS_PER_PAGE_DEFAULT = 8;
    private final MovieService movieService;
    private final PageValidator validator;

    public HomePageCommand() {
        movieService = new MovieServiceImpl();
        validator = new PageValidatorImpl();
    }

    public HomePageCommand(MovieService movieService, PageValidator validator) {
        this.movieService = movieService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String pageValue = request.getParameter(Parameter.PAGE);
        int page = validator.validate(pageValue);
        request.setAttribute(Attribute.PAGE, page);
        List<Movie> movies = movieService.getPage(page, ITEMS_PER_PAGE_DEFAULT);
        request.setAttribute(Attribute.MOVIES, movies);
        int numberOfPages = movieService.getNumberOfPages(ITEMS_PER_PAGE_DEFAULT);
        request.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Attribute.ITEMS_PER_PAGE, ITEMS_PER_PAGE_DEFAULT);
        return CommandResult.forward(Page.HOME);
    }
}
