package com.epam.movierating.logic;

import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Movie;

import java.util.List;

public interface CommentService {

    List<CommentDto> getMovieComments(Movie movie) throws ServiceException;

    void deleteCommentById(long id) throws ServiceException;

    long createNewComment(CommentDto commentDto) throws ServiceException;

}
