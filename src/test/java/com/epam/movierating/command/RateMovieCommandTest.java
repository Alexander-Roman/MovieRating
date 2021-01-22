package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.UserRatingService;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.dto.UserRatingDto;
import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class RateMovieCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";
    private static final String VALID_RATE_PARAM = "5";
    private static final int VALID_RATE = 5;
    private static final String INVALID_RATE_PARAM = "rate";
    private static final String CONTEXT_PATH = "contextPath";
    private static final Account ACCOUNT = new Account(VALID_ID, "userName", Role.USER, false);
    private static final UserRatingDto USER_RATING_DTO = new UserRatingDto(null, VALID_ID, VALID_ID, VALID_RATE);

    private UserRatingService userRatingService;
    private RateMovieCommand rateMovieCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;

    @BeforeMethod
    public void setUp() {
        userRatingService = Mockito.mock(UserRatingService.class);
        rateMovieCommand = new RateMovieCommand(userRatingService);
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        servletContext = Mockito.mock(ServletContext.class);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        rateMovieCommand.execute(request);
        //then
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenRateParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.RATE)).thenReturn(INVALID_RATE_PARAM);
        rateMovieCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldIncludeUserRating() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        rateMovieCommand.execute(request);
        //then
        verify(userRatingService, times(1)).includeUserRating(USER_RATING_DTO);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        CommandResult commandResult = rateMovieCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    private void normalExecuteScenario() {
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Attribute.ACCOUNT)).thenReturn(ACCOUNT);
        when(request.getParameter(Parameter.RATE)).thenReturn(VALID_RATE_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getContextPath()).thenReturn(CONTEXT_PATH);
    }
}
