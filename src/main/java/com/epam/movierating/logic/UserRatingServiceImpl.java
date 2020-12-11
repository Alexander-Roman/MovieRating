package com.epam.movierating.logic;

import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.UserRatingDtoDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.model.dto.UserRatingDto;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.entity.Movie;

import java.util.Optional;

public class UserRatingServiceImpl implements UserRatingService {

    private final DaoConnectionManagerFactory factory;

    public UserRatingServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Integer> getPersonalAssessment(Movie assessed, Account assessor) throws ServiceException {
        Long movieId = assessed.getId();
        Long accountId = assessor.getId();
        Optional<UserRatingDto> result;
        try (DaoConnectionManager manager = factory.create()) {
            UserRatingDtoDao userRatingDtoDao = manager.createUserRatingDtoDao();
            result = userRatingDtoDao.getByAssessorAndAssessed(movieId, accountId);
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
}
