package com.epam.movierating.dao;

import com.epam.movierating.model.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * Common interface for accessing application entity data. Contains CRUD methods for a specific entity.
 * @param <T> type of handling instances
 */
public interface Dao<T extends Identifiable> extends AutoCloseable {

    /**
     * Save a new object or update an existing one based on ID
     * @param object instance to create or update
     * @return ID value of new or updated object
     * @throws DaoException in case of errors
     */
    long save(T object) throws DaoException;

    /**
     * Returns list of all available objects
     * @return List of all objects
     * @throws DaoException in case of errors
     */
    List<T> findAll() throws DaoException;

    /**
     * Attempts to find an object by ID and returns Optional container with it if success.
     * Otherwise returns empty Optional.
     * @param id ID value of object to search
     * @return Optional with object found, or empty if found nothing
     * @throws DaoException in case of errors
     */
    Optional<T> find(long id) throws DaoException;

    /**
     * Removes the object at the specified ID
     * @param id ID value of object to delete
     * @throws DaoException in case of errors
     */
    void delete(long id) throws DaoException;

    /**
     * Method overriding restricts the type hierarchy of possible exceptions from AutoCloseable
     * @throws DaoException in case of errors
     */
    @Override
    void close() throws DaoException;
}
