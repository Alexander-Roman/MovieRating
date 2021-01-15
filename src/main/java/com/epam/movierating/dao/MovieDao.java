package com.epam.movierating.dao;

import com.epam.movierating.model.entity.Movie;

import java.util.List;

/**
 * Extending DAO interface for managing Movie
 */
public interface MovieDao extends Dao<Movie> {

    /**
     * Recalculates and updates movie rating according to user ratings
     * @param movieId ID of movie to update
     * @throws DaoException in case of errors
     */
    void updateMovieRatingById(long movieId) throws DaoException;

    /**
     * Returns the number of stored movies
     * @return all movies amount
     * @throws DaoException in case of errors
     */
    long getMoviesAmount() throws DaoException;

    /**
     * Returns a specified number of movies starting from a specified sequence number
     * @param amount maximum number of movies
     * @param from ordered number of the first
     * @return List of movies from a given range
     * @throws DaoException in case of errors
     */
    List<Movie> findBatch(int amount, int from) throws DaoException;
}
