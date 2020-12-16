package com.epam.movierating.logic;

import com.epam.movierating.model.dto.UserRatingDto;

import java.util.Optional;

public interface UserRatingService {

    Optional<Integer> getPersonalAssessment(long movieId, long accountId) throws ServiceException;

    void includeUserRating(UserRatingDto userRatingDto) throws ServiceException;

}
