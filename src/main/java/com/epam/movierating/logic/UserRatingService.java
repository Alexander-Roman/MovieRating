package com.epam.movierating.logic;

import com.epam.movierating.model.Account;
import com.epam.movierating.model.Movie;

import java.util.Optional;

public interface UserRatingService {

    Optional<Integer> getPersonalAssessment(Movie assessed, Account assessor) throws ServiceException;
}
