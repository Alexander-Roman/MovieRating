package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws DaoException;

}
