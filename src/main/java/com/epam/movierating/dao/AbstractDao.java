package com.epam.movierating.dao;

import com.epam.movierating.connection.ConnectionPool;
import com.epam.movierating.connection.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDao implements Closeable {

    private static final Logger LOGGER = LogManager.getLogger();
    private Connection connection;
    private Statement statement;

    public AbstractDao() throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void close() {
        try {
            closeStatement();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                closeConnection();
            } catch (DaoException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void closeStatement() throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                statement = null;
            }
        }
    }

    private void closeConnection() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                connection = null;
            }
        }
    }
}
