package com.epam.movierating.logic;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.UserRatingDtoDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.UserRatingDtoValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.UserRatingDto;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UserRatingServiceImplTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;
    private static final int ASSESSMENT = 5;
    private static final UserRatingDto USER_RATING_DTO_EXISTING = new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, ASSESSMENT);
    private static final UserRatingDto USER_RATING_DTO_CREATED = new UserRatingDto(null, VALID_ID, VALID_ID, ASSESSMENT);

    private DaoConnectionManagerFactory factory;
    private Validator<UserRatingDto> userRatingDtoValidator;
    private UserRatingServiceImpl userRatingService;
    private DaoConnectionManager daoConnectionManager;
    private UserRatingDtoDao userRatingDtoDao;
    private MovieDao movieDao;

    @BeforeMethod
    public void setUp() throws DaoException {
        factory = Mockito.mock(DaoConnectionManagerFactory.class);
        userRatingDtoValidator = Mockito.mock(UserRatingDtoValidator.class);
        userRatingService = new UserRatingServiceImpl(factory, userRatingDtoValidator);
        daoConnectionManager = Mockito.mock(DaoConnectionManager.class);
        userRatingDtoDao = Mockito.mock(UserRatingDtoDao.class);
        movieDao = Mockito.mock(MovieDao.class);

        when(factory.create()).thenReturn(daoConnectionManager);
        when(daoConnectionManager.createUserRatingDtoDao()).thenReturn(userRatingDtoDao);
        when(userRatingDtoValidator.isValid(any())).thenReturn(true);
        when(userRatingDtoDao.getByAssessedAndAssessor(VALID_ID, VALID_ID)).thenReturn(Optional.of(USER_RATING_DTO_EXISTING));
        when(daoConnectionManager.createMovieDao()).thenReturn(movieDao);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPersonalAssessmentShouldThrowExceptionWhenMovieIdInvalid() throws ServiceException {
        //given
        //when
        userRatingService.getPersonalAssessment(INVALID_ID, VALID_ID);
        //then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPersonalAssessmentShouldThrowExceptionWhenAccountIdInvalid() throws ServiceException {
        //given
        //when
        userRatingService.getPersonalAssessment(VALID_ID, INVALID_ID);
        //then
    }

    @Test
    public void testGetPersonalAssessmentShouldReturnCorrectAssessment() throws ServiceException {
        //given
        //when
        Optional<Integer> actual = userRatingService.getPersonalAssessment(VALID_ID, VALID_ID);
        //then
        Optional<Integer> expected = Optional.of(ASSESSMENT);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetPersonalAssessmentShouldReturnEmptyWhenAssessmentNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(userRatingDtoDao.getByAssessedAndAssessor(VALID_ID, VALID_ID)).thenReturn(Optional.empty());
        Optional<Integer> actual = userRatingService.getPersonalAssessment(VALID_ID, VALID_ID);
        //then
        Optional<Integer> expected = Optional.empty();
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testIncludeUserRatingShouldThrowExceptionWhenUserRatingDtoInvalid() throws ServiceException {
        //given
        //when
        when(userRatingDtoValidator.isValid(any())).thenReturn(false);
        userRatingService.includeUserRating(USER_RATING_DTO_CREATED);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testIncludeUserRatingShouldThrowExceptionWhenUserRatingDtoIdIsPresent() throws ServiceException {
        //given
        //when
        userRatingService.includeUserRating(USER_RATING_DTO_EXISTING);
        //then
    }

    @Test
    public void testIncludeUserRatingShouldSaveUserRatingWithinTransaction() throws ServiceException, DaoException {
        //given
        InOrder inOrder = Mockito.inOrder(daoConnectionManager, userRatingDtoDao);
        //when
        userRatingService.includeUserRating(USER_RATING_DTO_CREATED);
        //then
        inOrder.verify(daoConnectionManager, times(1)).beginTransaction();
        inOrder.verify(userRatingDtoDao, times(1)).save(USER_RATING_DTO_CREATED);
        inOrder.verify(daoConnectionManager, times(1)).commitTransaction();
    }

    @Test
    public void testIncludeUserRatingShouldUpdateMovieRatingWithinTransaction() throws ServiceException, DaoException {
        //given
        InOrder inOrder = Mockito.inOrder(daoConnectionManager, movieDao);
        //when
        userRatingService.includeUserRating(USER_RATING_DTO_CREATED);
        //then
        inOrder.verify(daoConnectionManager, times(1)).beginTransaction();
        inOrder.verify(movieDao, times(1)).updateMovieRatingById(VALID_ID);
        inOrder.verify(daoConnectionManager, times(1)).commitTransaction();
    }
}
