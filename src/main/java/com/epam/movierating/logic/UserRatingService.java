package com.epam.movierating.logic;

import com.epam.movierating.model.dto.UserRatingDto;

import java.util.Optional;

/**
 * The interface describes a logic class for operations with UserRatings
 */
public interface UserRatingService {

    /**
     * Finds the assessment of a specific user for a specific movie.
     * Returns result in Optional container, or empty Optional if such assessment is not present
     * @param movieId assessed Movie ID
     * @param accountId assessor Account ID
     * @return Optional of assessment value
     * @throws ServiceException in case of errors
     */
    Optional<Integer> getPersonalAssessment(long movieId, long accountId) throws ServiceException;

    /**
     * Saves the new assessment with the recalculation of the movie rating
     * @param userRatingDto transfer object with data to operate
     * @throws ServiceException in case of errors
     */
    void includeUserRating(UserRatingDto userRatingDto) throws ServiceException;
}
