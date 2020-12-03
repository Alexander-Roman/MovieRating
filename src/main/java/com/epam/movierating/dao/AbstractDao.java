package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.entity.Identifiable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final RowMapper<T> rowMapper;
    private Connection connection;

    public AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected Optional<Object> selectScalar(String sql, Object... parameters) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = getResultSet(preparedStatement, parameters);
            if (resultSet.next()) {
                Object result = resultSet.getObject(1);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> selectSingle(String sql, Object... parameters) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = getResultSet(preparedStatement, parameters);
            if (resultSet.next()) {
                T result = rowMapper.map(resultSet);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected List<T> selectSeveral(String sql, Object... parameters) throws DaoException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = getResultSet(preparedStatement, parameters);
            while (resultSet.next()) {
                T result = rowMapper.map(resultSet);
                results.add(result);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return results;
    }

    private ResultSet getResultSet(PreparedStatement preparedStatement, Object[] parameters) throws DaoException {
        try {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
