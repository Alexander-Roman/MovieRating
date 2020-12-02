package com.epam.movierating.dao;

import com.epam.movierating.entity.Movie;

import java.util.List;

public interface MovieDao extends Dao<Movie> {

    List<Movie> findSetSorted(int amount, int from) throws DaoException;

}
