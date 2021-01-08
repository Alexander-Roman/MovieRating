package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.entity.Movie;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

public class DeleteMovieCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";
    private static final Movie MOVIE = new Movie(VALID_ID, "Title", "Director", 2021, "Synopsis", null, null);

    private MovieService movieService;
    private DeleteMovieCommand deleteMovieCommand;
    private HttpServletRequest request;

    @BeforeMethod
    public void setUp() {
        movieService = Mockito.mock(MovieService.class);
        deleteMovieCommand = new DeleteMovieCommand(movieService);
        request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(movieService.getById(anyLong())).thenReturn(MOVIE);
        CommandResult commandResult = deleteMovieCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldDeleteMovie() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(movieService.getById(anyLong())).thenReturn(MOVIE);
        deleteMovieCommand.execute(request);
        //then
        verify(movieService, times(1)).deleteMovieById(VALID_ID);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        when(movieService.getById(anyLong())).thenReturn(MOVIE);
        deleteMovieCommand.execute(request);
        //then
    }
}
