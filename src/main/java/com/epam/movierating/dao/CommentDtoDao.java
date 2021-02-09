package com.epam.movierating.dao;

import com.epam.movierating.model.dto.CommentDto;

import java.util.List;

/**
 * Extending DAO interface for managing CommentDto
 */
public interface CommentDtoDao extends Dao<CommentDto> {

    /**
     * Finds all comments related to a given movie
     * @param movieId ID of the movie to which comments will be searched
     * @return List of all comments related to the specified movie
     * @throws DaoException in case of errors
     */
    List<CommentDto> getByMovieId(Long movieId) throws DaoException;

}
