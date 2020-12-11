package com.epam.movierating.logic;

import com.epam.movierating.model.CommentTo;
import com.epam.movierating.model.Movie;

import java.util.List;

public interface CommentService {

    List<CommentTo> getMovieComments(Movie movie) throws ServiceException;
}
