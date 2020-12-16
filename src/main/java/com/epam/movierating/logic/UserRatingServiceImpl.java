package com.epam.movierating.logic;

import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.UserRatingDtoDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.UserRatingDto;

import java.util.Optional;

public class UserRatingServiceImpl implements UserRatingService {

    private static final long MIN_ID_VALUE = 1L;
    private final DaoConnectionManagerFactory factory;
    private final Validator<UserRatingDto> userRatingDtoValidator;

    public UserRatingServiceImpl(DaoConnectionManagerFactory factory, Validator<UserRatingDto> userRatingDtoValidator) {
        this.factory = factory;
        this.userRatingDtoValidator = userRatingDtoValidator;
    }

    @Override
    public Optional<Integer> getPersonalAssessment(long movieId, long accountId) throws ServiceException {
        if (movieId < MIN_ID_VALUE) {
            throw new ServiceException("Invalid movie id value: " + movieId);
        }
        if (accountId < MIN_ID_VALUE) {
            throw new ServiceException("Invalid account id value: " + accountId);
        }
        Optional<UserRatingDto> result;
        try (DaoConnectionManager manager = factory.create()) {
            UserRatingDtoDao userRatingDtoDao = manager.createUserRatingDtoDao();
            result = userRatingDtoDao.getByAssessedAndAssessor(movieId, accountId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (result.isPresent()) {
            UserRatingDto userRatingDto = result.get();
            Integer assessment = userRatingDto.getAssessment();
            return Optional.of(assessment);
        }
        return Optional.empty();
    }

    @Override
    public void includeUserRating(UserRatingDto userRatingDto) throws ServiceException {
        if (!userRatingDtoValidator.isValid(userRatingDto)) {
            throw new ServiceException("Invalid UserRatingDto object: " + userRatingDto);
        }
        Long movieId = userRatingDto.getAssessedId();
        try (DaoConnectionManager manager = factory.create()) {
            UserRatingDtoDao userRatingDtoDao = manager.createUserRatingDtoDao();
            MovieDao movieDao = manager.createMovieDao();

            manager.beginTransaction();
            userRatingDtoDao.save(userRatingDto);
            movieDao.updateMovieRatingById(movieId);
            manager.commitTransaction();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "factory=" + factory +
                ", userRatingDtoValidator=" + userRatingDtoValidator +
                '}';
    }
}
