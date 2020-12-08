package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.UserRatingDao;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.UserRating;

public interface DaoConnectionManager extends AutoCloseable {

    MovieDao createMovieDao();

    AccountDao createAccountDao();

    void beginTransaction() throws DaoException;

    void commitTransaction() throws DaoException;
}
