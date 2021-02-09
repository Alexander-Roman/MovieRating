package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.validator.PosterValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.entity.Movie;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SaveMovieCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";
    private static final String TITLE_PARAM = "title";
    private static final String DIRECTOR_PARAM = "director";
    private static final String VALID_RELEASE_YEAR_PARAM = "2021";
    private static final int VALID_RELEASE_YEAR = 2021;
    private static final String INVALID_RELEASE_YEAR_PARAM = "year";
    private static final String SYNOPSIS_PARAM = "synopsis";
    private static final String POSTER_PATH_PARAM = "posterPath";
    private static final String VALID_RATING_PARAM = "5.6";
    private static final double VALID_RATING = 5.6;
    private static final String INVALID_RATING_PARAM = "rating";
    private static final String REAL_PATH = "realPath";
    private static final String CONTEXT_PATH = "contextPath";
    private static final Movie MOVIE = new Movie(VALID_ID, TITLE_PARAM, DIRECTOR_PARAM, VALID_RELEASE_YEAR, SYNOPSIS_PARAM, POSTER_PATH_PARAM, VALID_RATING);

    private MovieService movieService;
    private Validator<Part> posterValidator;
    private SaveMovieCommand saveMovieCommand;
    private HttpServletRequest request;
    private ServletContext servletContext;

    @BeforeMethod
    public void setUp() {
        movieService = Mockito.mock(MovieService.class);
        posterValidator = Mockito.mock(PosterValidator.class);
        saveMovieCommand = new SaveMovieCommand(movieService, posterValidator);
        request = Mockito.mock(HttpServletRequest.class);
        servletContext = Mockito.mock(ServletContext.class);
    }

    @Test
    public void testExecuteShouldSaveMovieWhenIdParameterNotDefined() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.ID)).thenReturn("");
        saveMovieCommand.execute(request);
        //then
        verify(movieService, times(1)).save(any());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        saveMovieCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldSaveMovieWhenReleaseYearParameterNotDefined() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.RELEASE_YEAR)).thenReturn("");
        saveMovieCommand.execute(request);
        //then
        verify(movieService, times(1)).save(any());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenReleaseYearParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.RELEASE_YEAR)).thenReturn(INVALID_RELEASE_YEAR_PARAM);
        saveMovieCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldSaveMovieWhenRatingParameterNotDefined() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.RATE)).thenReturn(null);
        saveMovieCommand.execute(request);
        //then
        verify(movieService, times(1)).save(any());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenRatingParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        when(request.getParameter(Parameter.RATING)).thenReturn(INVALID_RATING_PARAM);
        saveMovieCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldSaveCorrectMovie() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        saveMovieCommand.execute(request);
        //then
        verify(movieService, times(1)).save(MOVIE);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        CommandResult commandResult = saveMovieCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    private void normalExecuteScenario() {
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getParameter(Parameter.TITLE)).thenReturn(TITLE_PARAM);
        when(request.getParameter(Parameter.DIRECTOR)).thenReturn(DIRECTOR_PARAM);
        when(request.getParameter(Parameter.RELEASE_YEAR)).thenReturn(VALID_RELEASE_YEAR_PARAM);
        when(request.getParameter(Parameter.SYNOPSIS)).thenReturn(SYNOPSIS_PARAM);
        when(request.getParameter(Parameter.POSTER_PATH)).thenReturn(POSTER_PATH_PARAM);
        when(request.getParameter(Parameter.RATING)).thenReturn(VALID_RATING_PARAM);
        when(posterValidator.isValid(any())).thenReturn(true);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath(anyString())).thenReturn(REAL_PATH);
        when(servletContext.getContextPath()).thenReturn(CONTEXT_PATH);
    }
}
