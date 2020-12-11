package com.epam.movierating.dao;

import com.epam.movierating.model.dto.UserRatingDto;

import java.util.Optional;

public interface UserRatingDtoDao extends Dao<UserRatingDto> {

    Optional<UserRatingDto> getByAssessorAndAssessed(Long movieId, Long accountId) throws DaoException;

}
