package com.epam.movierating.dao;

import com.epam.movierating.model.dto.UserRatingDto;

import java.util.Optional;

/**
 * Extending DAO interface for managing UserRatingDto
 */
public interface UserRatingDtoDao extends Dao<UserRatingDto> {

    /**
     * Finds UserRatingDto specified by assessed movie ID and assessor account ID.
     * Returns in Optional, or empty Optional if no matching found.
     * @param movieId ID UserRatingDto assessed movie
     * @param accountId ID UserRatingDto assessor account
     * @return Optional of UserRatingDto found, or empty if found nothing
     * @throws DaoException in case of errors
     */
    Optional<UserRatingDto> getByAssessedAndAssessor(Long movieId, Long accountId) throws DaoException;
}
