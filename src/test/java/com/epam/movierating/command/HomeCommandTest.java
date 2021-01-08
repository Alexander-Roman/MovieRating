package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.NotFoundException;
import com.epam.movierating.logic.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class HomeCommandTest {

    private static final String VALID_PAGE_PARAM = "1";
    private static final int VALID_PAGE = 1;
    private static final String INVALID_PAGE_PARAM = "page";

    private MovieService movieService;
    private HomeCommand homeCommand;
    private HttpServletRequest request;

    @BeforeMethod
    public void setUp() {
        movieService = Mockito.mock(MovieService.class);
        homeCommand = new HomeCommand(movieService);
        request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void testExecuteShouldReturnForwardCommandResult() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        CommandResult commandResult = homeCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertFalse(actual);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenPageParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(INVALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        homeCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldGetMoviesListForGivenPage() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        homeCommand.execute(request);
        //then
        verify(movieService, times(1)).getPage(eq(VALID_PAGE), anyInt());
    }

    @Test
    public void testExecuteShouldSetItemsPerPageAttribute() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        homeCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(eq(Attribute.ITEMS_PER_PAGE), any());
    }

    @Test
    public void testExecuteShouldSetNumberOfPagesAttribute() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        homeCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.NUMBER_OF_PAGES, 1);
    }

    @Test
    public void testExecuteShouldSetPageAttribute() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        homeCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.PAGE, VALID_PAGE);
    }

    @Test
    public void testExecuteShouldSetMoviesAttribute() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(1);
        when(movieService.getPage(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        homeCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.MOVIES, Collections.emptyList());
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotExist() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(movieService.getNumberOfPages(anyInt())).thenReturn(0);
        homeCommand.execute(request);
        //then
    }
}
