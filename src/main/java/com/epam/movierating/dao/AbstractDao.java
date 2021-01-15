package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.Identifiable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A generic abstract class for all DAOs that encapsulates the work with database connections.
 * Provides a set of protected methods for all query options.
 * @param <T> type of handling instances
 */
public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    /**
     * Generic RowMapper for receiving objects from result sets
     */
    private final RowMapper<T> rowMapper;

    /**
     * A connection (session) with database
     */
    private final Connection connection;

    /**
     * Class fields are immutable. A single constructor sets all of them.
     * @param connection Connection implementation
     * @param rowMapper RowMapper implementation
     */
    public AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    /**
     * Updates or inserts one record of database
     * @param sql prepared query
     * @param parameters query parameters
     * @return Optional container with ID value generated, or empty when existing record updated
     * @throws DaoException in case of errors
     */
    protected Optional<Long> updateSingle(String sql, Object... parameters) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.executeUpdate();
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            if (generatedKeysResultSet.next()) {
                Long id = generatedKeysResultSet.getLong(1);
                return Optional.of(id);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Gets scalar values as counts, max, min, average, ect.
     * @param sql prepared query
     * @param parameters query parameters
     * @return Optional container with an object without casting, or empty when no value found
     * @throws DaoException in case of errors
     */
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

    /**
     * Gets single specified object
     * @param sql prepared query
     * @param parameters query parameters
     * @return Optional container with required entity, or empty when found nothing
     * @throws DaoException in case of errors
     */
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

    /**
     * Gets list of specified objects
     * @param sql prepared query
     * @param parameters query parameters
     * @return List of required entities, or empty List when no matches found
     * @throws DaoException in case of errors
     */
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
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractDao<?> that = (AbstractDao<?>) o;
        return Objects.equals(rowMapper, that.rowMapper) &&
                Objects.equals(connection, that.connection);
    }

    @Override
    public int hashCode() {
        int result = rowMapper != null ? rowMapper.hashCode() : 0;
        result = 31 * result + (connection != null ? connection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "rowMapper=" + rowMapper +
                ", connection=" + connection +
                '}';
    }
}
