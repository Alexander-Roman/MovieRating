package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.UserRatingService;
import com.epam.movierating.model.dto.UserRatingDto;
import com.epam.movierating.model.entity.Account;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RateMovieCommand implements Command {

    private static final String MOVIE_COMMAND_PATH = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.MOVIE;
    private final UserRatingService userRatingService;

    public RateMovieCommand(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String movieIdParameter = request.getParameter(Parameter.ID);
        long movieId = Long.parseLong(movieIdParameter);

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        Long accountId = account.getId();

        String rateParameter = request.getParameter(Parameter.RATE);
        int rate = Integer.parseInt(rateParameter);

        UserRatingDto userRatingDto = new UserRatingDto(null, movieId, accountId, rate);
        userRatingService.includeUserRating(userRatingDto);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MOVIE_COMMAND_PATH + "&" + Parameter.ID + "=" + movieId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userRatingService=" + userRatingService +
                '}';
    }
}
