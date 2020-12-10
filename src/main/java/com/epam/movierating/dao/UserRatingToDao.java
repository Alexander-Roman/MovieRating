package com.epam.movierating.dao;

import com.epam.movierating.entity.UserRating;
import com.epam.movierating.entity.UserRatingTo;

import java.util.Optional;

public interface UserRatingToDao extends Dao<UserRatingTo> {

    Optional<UserRatingTo> getByAssessorAndAssessed(Long movieId, Long accountId) throws DaoException;

}
