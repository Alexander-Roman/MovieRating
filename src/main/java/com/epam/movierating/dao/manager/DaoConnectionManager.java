package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.entity.Account;

public interface DaoConnectionManager extends AutoCloseable {

    MovieDao createMovieDao();

    AccountDao createAccountDao();
}
