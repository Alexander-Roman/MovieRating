package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.validator.PageParameterValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomeCommand implements Command {

    private static final int ITEMS_PER_PAGE_DEFAULT = 8;
    private final MovieService movieService;
    private final PageParameterValidator validator;

    public HomeCommand(MovieService movieService, PageParameterValidator validator) {
        this.movieService = movieService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String pageValue = request.getParameter(Parameter.PAGE);

        int page = validator.validate(pageValue);
        request.setAttribute(Attribute.PAGE, page);
        request.setAttribute(Attribute.ITEMS_PER_PAGE, ITEMS_PER_PAGE_DEFAULT);

        int numberOfPages = movieService.getNumberOfPages(ITEMS_PER_PAGE_DEFAULT);
        request.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);

        List<Movie> movies = movieService.getPage(page, ITEMS_PER_PAGE_DEFAULT);
        request.setAttribute(Attribute.MOVIES, movies);

        return CommandResult.forward(Page.HOME);
    }
}
