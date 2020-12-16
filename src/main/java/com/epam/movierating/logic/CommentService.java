package com.epam.movierating.logic;

import com.epam.movierating.model.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getMovieComments(long movieId) throws ServiceException;

    void deleteCommentById(long id) throws ServiceException;

    long createNewComment(CommentDto commentDto) throws ServiceException;

}
