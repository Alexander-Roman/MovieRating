package com.epam.movierating.logic;

import com.epam.movierating.model.entity.Movie;

/**
 * The interface describes a logic class for operations with Movies
 */
public interface MovieService extends PaginationService<Movie> {

    /**
     * Finds and returns a movie with the specified ID
     * @param id specified ID
     * @return Movie
     * @throws ServiceException in case of errors or if movie not found
     */
    Movie getById(long id) throws ServiceException;

    /**
     * Saves a new movie
     * @param movie object to save
     * @return new ID generated
     * @throws ServiceException in case of errors
     */
    long save(Movie movie) throws ServiceException;

    /**
     * Deletes a movie with the specified ID
     * @param id specified ID
     * @throws ServiceException in case of errors or if movie not found
     */
    void deleteMovieById(long id) throws ServiceException;
}
