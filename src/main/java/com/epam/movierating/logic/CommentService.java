package com.epam.movierating.logic;

import com.epam.movierating.model.dto.CommentDto;

import java.util.List;

/**
 * The interface describes a logic class for operations with Comments
 */
public interface CommentService {

    /**
     * Gets a list of comments for a given movie
     * @param movieId specified Movie ID
     * @return List of CommentDto objects
     * @throws ServiceException in case of errors
     */
    List<CommentDto> getMovieComments(long movieId) throws ServiceException;

    /**
     * Removes the specified comment
     * @param id specified Comment ID
     * @throws ServiceException in case of errors
     */
    void deleteCommentById(long id) throws ServiceException;

    /**
     * Saves a new comment
     * @param commentDto transfer object with data to save
     * @return ID generated
     * @throws ServiceException in case of errors
     */
    long createNewComment(CommentDto commentDto) throws ServiceException;
}
