package com.epam.movierating.logic;

import com.epam.movierating.dao.UserRatingToDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.entity.UserRatingTo;

import java.util.Optional;

public class UserRatingServiceImpl extends AbstractService implements UserRatingService {

    public UserRatingServiceImpl(DaoConnectionManagerFactory factory) {
        super(factory);
    }

    @Override
    public Optional<Integer> getPersonalAssessment(Movie assessed, Account assessor) throws ServiceException {
        if (assessed == null || assessor == null) {
            return Optional.empty();
        }
        Long movieId = assessed.getId();
        Long accountId = assessor.getId();
        Optional<UserRatingTo> result;
        try (DaoConnectionManager manager = createDaoConnectionManager()) {
            UserRatingToDao userRatingToDao = manager.createUserRatingToDao();
            result = userRatingToDao.getByAssessorAndAssessed(movieId, accountId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (result.isPresent()) {
            UserRatingTo userRatingTo = result.get();
            Integer assessment = userRatingTo.getAssessment();
            return Optional.of(assessment);
        }
        return Optional.empty();
    }
}
