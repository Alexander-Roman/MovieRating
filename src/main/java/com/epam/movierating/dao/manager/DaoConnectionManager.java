package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.MovieDao;

public interface DaoConnectionManager extends AutoCloseable {

    MovieDao createMovieDao();
}
