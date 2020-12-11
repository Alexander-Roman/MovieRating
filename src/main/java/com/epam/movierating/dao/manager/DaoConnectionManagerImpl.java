package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;
import com.epam.movierating.dao.mapper.AccountRowMapper;
import com.epam.movierating.dao.mapper.CommentToRowMapper;
import com.epam.movierating.dao.mapper.MovieRowMapper;
import com.epam.movierating.dao.mapper.UserRatingToRowMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConnectionManagerImpl implements DaoConnectionManager {

    private Connection connection;

    public DaoConnectionManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException commitException) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new DaoException(rollbackException);
            }
        }
    }

    @Override
    public MovieDao createMovieDao() {
        return new MovieDaoImpl(connection, new MovieRowMapper());
    }

    @Override
    public AccountDao createAccountDao() {
        return new AccountDaoImpl(connection, new AccountRowMapper());
    }

    @Override
    public UserRatingToDao createUserRatingToDao() {
        return new UserRatingToDaoImpl(connection, new UserRatingToRowMapper());
    }

    @Override
    public CommentToDao createCommentToDao() {
        return new CommentToDaoImpl(connection, new CommentToRowMapper(new AccountRowMapper()));
    }

    @Override
    public void close() throws DaoException {
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
