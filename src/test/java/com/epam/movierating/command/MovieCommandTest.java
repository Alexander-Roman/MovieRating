package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.UserRatingService;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.entity.Movie;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class MovieCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";
    private static final Movie MOVIE = new Movie(VALID_ID, "Title", "Director", 2021, "Synopsis", null, null);
    private static final Account ACCOUNT = new Account(VALID_ID, "userName", Role.USER, false);
    private static final int ASSESSMENT = 5;

    private MovieService movieService;
    private UserRatingService userRatingService;
    private CommentService commentService;
    private MovieCommand movieCommand;
    private HttpServletRequest request;
    private HttpSession session;

    @BeforeMethod
    public void setUp() {
        movieService = Mockito.mock(MovieService.class);
        userRatingService = Mockito.mock(UserRatingService.class);
        commentService = Mockito.mock(CommentService.class);
        movieCommand = new MovieCommand(movieService, userRatingService, commentService);
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        movieCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldFindMovie() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(movieService, times(1)).getById(VALID_ID);
    }

    @Test
    public void testExecuteShouldSetMovieAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.MOVIE, MOVIE);
    }

    @Test
    public void testExecuteShouldFindMovieComments() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(commentService, times(1)).getMovieComments(VALID_ID);
    }

    @Test
    public void testExecuteShouldSetCommentsAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.COMMENTS, Collections.emptyList());
    }

    @Test
    public void testExecuteShouldGetPersonalAssessmentWhenUserAuthorized() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(userRatingService, times(1)).getPersonalAssessment(VALID_ID, VALID_ID);
    }

    @Test
    public void testExecuteShouldNotGetPersonalAssessmentWhenUserNotAuthorized() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(session.getAttribute(Attribute.ACCOUNT)).thenReturn(null);
        movieCommand.execute(request);
        //then
        verify(userRatingService, never()).getPersonalAssessment(anyLong(), anyLong());
    }

    @Test
    public void testExecuteShouldSetAssessmentAttributeWhenFound() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        movieCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.PERSONAL_RATE, ASSESSMENT);
    }

    @Test
    public void testExecuteShouldNotSetAssessmentAttributeWhenNotFound() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(userRatingService.getPersonalAssessment(anyLong(), anyLong())).thenReturn(Optional.empty());
        movieCommand.execute(request);
        //then
        verify(request, never()).setAttribute(Attribute.PERSONAL_RATE, ASSESSMENT);
    }

    @Test
    public void testExecuteShouldReturnForwardCommandResult() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        CommandResult commandResult = movieCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertFalse(actual);
    }

    private void normalExecuteScenario() throws ServiceException {
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(movieService.getById(anyLong())).thenReturn(MOVIE);
        when(commentService.getMovieComments(anyLong())).thenReturn(Collections.emptyList());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Attribute.ACCOUNT)).thenReturn(ACCOUNT);
        when(userRatingService.getPersonalAssessment(anyLong(), anyLong())).thenReturn(Optional.of(ASSESSMENT));
    }
}
