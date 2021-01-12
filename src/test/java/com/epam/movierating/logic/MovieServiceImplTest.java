package com.epam.movierating.logic;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.MovieValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.entity.Movie;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;
    private static final Movie MOVIE = new Movie(VALID_ID, "title", "director", 2021, "synopsis", "posterPath", 5.6);
    private static final int VALID_ITEMS_PER_PAGE = 8;
    private static final int INVALID_ITEMS_PER_PAGE = 0;
    private static final int VALID_PAGE = 8;
    private static final int INVALID_PAGE = 0;
    private static final long MOVIES_AMOUNT = 32L;

    private DaoConnectionManagerFactory factory;
    private Validator<Movie> movieValidator;
    private MovieServiceImpl movieService;
    private DaoConnectionManager daoConnectionManager;
    private MovieDao movieDao;

    @BeforeMethod
    public void setUp() throws DaoException {
        factory = Mockito.mock(DaoConnectionManagerFactory.class);
        movieValidator = Mockito.mock(MovieValidator.class);
        movieService = new MovieServiceImpl(factory, movieValidator);
        daoConnectionManager = Mockito.mock(DaoConnectionManager.class);
        movieDao = Mockito.mock(MovieDao.class);

        when(factory.create()).thenReturn(daoConnectionManager);
        when(daoConnectionManager.createMovieDao()).thenReturn(movieDao);
        when(movieDao.getMoviesAmount()).thenReturn(MOVIES_AMOUNT);
        when(movieDao.find(VALID_ID)).thenReturn(Optional.of(MOVIE));
        when(movieValidator.isValid(any())).thenReturn(true);
        when(movieDao.save(MOVIE)).thenReturn(VALID_ID);
    }

    @Test(dataProvider = "ValidPaginationParametersProvider")
    public void testGetNumberOfPagesShouldReturnCorrectValue(long numberOfItems, int itemsPerPage, int numberOfPagesExpected) throws ServiceException, DaoException {
        //given
        //when
        when(movieDao.getMoviesAmount()).thenReturn(numberOfItems);
        int actual = movieService.getNumberOfPages(itemsPerPage);
        //then
        Assert.assertEquals(actual, numberOfPagesExpected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetNumberOfPagesShouldThrowExceptionWhenItemsPerPageValueInvalid() throws ServiceException {
        //given
        //when
        movieService.getNumberOfPages(INVALID_ITEMS_PER_PAGE);
        //then
    }

    @Test
    public void testGetPageShouldFindBatchOfItems() throws ServiceException, DaoException {
        //given
        //when
        movieService.getPage(VALID_PAGE, VALID_ITEMS_PER_PAGE);
        //then
        verify(movieDao, times(1)).findBatch(anyInt(), anyInt());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPageShouldThrowExceptionWhenPageParameterInvalid() throws ServiceException {
        //given
        //when
        movieService.getPage(INVALID_PAGE, VALID_ITEMS_PER_PAGE);
        //then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPageShouldThrowExceptionWhenItemsPerPageParameterInvalid() throws ServiceException {
        //given
        //when
        movieService.getPage(VALID_PAGE, INVALID_ITEMS_PER_PAGE);
        //then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByIdShouldThrowExceptionWhenIdInvalid() throws ServiceException {
        //given
        //when
        movieService.getById(INVALID_ID);
        //then
    }

    @Test
    public void testGetByIdShouldReturnMovieFound() throws ServiceException {
        //given
        //when
        Movie actual = movieService.getById(VALID_ID);
        //then
        Assert.assertEquals(actual, MOVIE);
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testGetByIdShouldThrowExceptionWhenMovieNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(movieDao.find(VALID_ID)).thenReturn(Optional.empty());
        movieService.getById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testSaveShouldThrowExceptionWhenMovieInvalid() throws ServiceException {
        //given
        //when
        when(movieValidator.isValid(any())).thenReturn(false);
        movieService.save(MOVIE);
        //then
    }

    @Test
    public void testSaveShouldReturnSavedMovieId() throws ServiceException {
        //given
        //when
        long actual = movieService.save(MOVIE);
        //then
        Assert.assertEquals(actual, VALID_ID);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteMovieByIdShouldThrowExceptionWhenIdInvalid() throws ServiceException {
        //given
        //when
        movieService.deleteMovieById(INVALID_ID);
        //then
    }

    @Test
    public void testDeleteMovieByIdShouldDeleteMovie() throws ServiceException, DaoException {
        //given
        //when
        movieService.deleteMovieById(VALID_ID);
        //then
        verify(movieDao, times(1)).delete(VALID_ID);
    }

    @DataProvider(name = "ValidPaginationParametersProvider")
    public Object[][] provideValidPaginationParameters() {
        return new Number[][]{
                {0L, 8, 1},
                {7L, 8, 1},
                {8L, 8, 1},
                {9L, 8, 2},
                {99L, 100, 1},
                {100L, 99, 2}
        };
    }
}
