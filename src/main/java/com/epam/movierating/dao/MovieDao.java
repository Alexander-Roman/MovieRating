package com.epam.movierating.dao;

import com.epam.movierating.model.Movie;

import java.util.List;

public interface MovieDao extends Dao<Movie> {

    List<Movie> findBatch(int amount, int from) throws DaoException;

    long getMoviesAmount() throws DaoException;
}
