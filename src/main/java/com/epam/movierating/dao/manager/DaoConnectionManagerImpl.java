package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;
import com.epam.movierating.dao.mapper.*;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.dto.UserRatingDto;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.model.entity.Movie;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConnectionManagerImpl implements DaoConnectionManager {

    private final Connection connection;

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
        RowMapper<Movie> rowMapper = new MovieRowMapper();
        return new MovieDaoImpl(connection, rowMapper);
    }

    @Override
    public AccountDao createAccountDao() {
        RowMapper<Account> rowMapper = new AccountRowMapper();
        return new AccountDaoImpl(connection, rowMapper);
    }

    @Override
    public UserRatingDtoDao createUserRatingDtoDao() {
        RowMapper<UserRatingDto> rowMapper = new UserRatingDtoRowMapper();
        return new UserRatingDtoDaoImpl(connection, rowMapper);
    }

    @Override
    public CommentDtoDao createCommentDtoDao() {
        RowMapper<Account> accountRowMapper = new AccountRowMapper();
        RowMapper<CommentDto> commentDtoRowMapper = new CommentDtoRowMapper(accountRowMapper);
        return new CommentDtoDaoImpl(connection, commentDtoRowMapper);
    }

    @Override
    public void close() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }
}
