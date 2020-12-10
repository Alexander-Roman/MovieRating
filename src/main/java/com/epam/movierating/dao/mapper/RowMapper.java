package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;

import java.sql.ResultSet;
import java.util.Map;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws DaoException;

    /*
    Map<String, String> unmap(T object);

     */
}
