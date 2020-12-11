package com.epam.movierating.dao;

import com.epam.movierating.model.dto.CommentDto;

import java.util.List;

public interface CommentDtoDao extends Dao<CommentDto> {

    List<CommentDto> getByMovieId(Long movieId) throws DaoException;

}
