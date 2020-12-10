package com.epam.movierating.dao;

import com.epam.movierating.entity.CommentTo;

import java.util.List;

public interface CommentToDao extends Dao<CommentTo> {

    List<CommentTo> getByMovieId(Long movieId) throws DaoException;
}
