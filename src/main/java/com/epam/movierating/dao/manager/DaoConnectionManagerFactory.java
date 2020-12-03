package com.epam.movierating.dao.manager;

import com.epam.movierating.connection.ConnectionPool;
import com.epam.movierating.connection.ConnectionPoolException;
import com.epam.movierating.dao.DaoException;

import java.sql.Connection;

public class DaoConnectionManagerFactory {

    public DaoConnectionManager create() throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            return new DaoConnectionManagerImpl(connection);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}
