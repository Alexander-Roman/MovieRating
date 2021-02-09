package com.epam.movierating.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Describes a class that creates and returns an expected object based on the data in the current row of ResultSet
 * @param <T> type of object to be retrieved from the database
 */
public interface RowMapper<T> {

    /**
     * Creates an object from data of current ResultSet row
     * @param resultSet table of data representing a database result set
     * @return expected type instance
     * @throws SQLException in case of errors
     */
    T map(ResultSet resultSet) throws SQLException;
}
