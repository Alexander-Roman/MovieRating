package com.epam.movierating.logic;

import com.epam.movierating.dao.UserRatingToDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.entity.CommentTo;
import com.epam.movierating.entity.Movie;

import java.util.List;

public interface CommentService {

    List<CommentTo> getMovieComments(Movie movie) throws ServiceException;
}
