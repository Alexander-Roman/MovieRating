package com.epam.movierating.dao;

import com.epam.movierating.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> extends AutoCloseable {

    long save(T object) throws DaoException;

    List<T> findAll() throws DaoException;

    Optional<T> find(long id) throws DaoException;

    void delete(long id) throws DaoException;

    long getElementsAmount() throws DaoException;
}
