package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConnectionManagerImpl implements DaoConnectionManager {

    private Connection connection;

    public DaoConnectionManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MovieDao createMovieDao() {
        return new MovieDaoImpl(connection);
    }

    @Override
    public AccountDao createAccountDao() {
        return new AccountDaoImpl(connection);
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
