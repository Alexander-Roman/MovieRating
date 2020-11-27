package com.epam.movierating.dao;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public interface Dao<T> extends Closeable {

    //Optional<T> create(T object);

    List<T> getAll() throws DaoException;

    //Optional<T> getById(long id);

    //Optional<T> updateById(T updated);

    //boolean deleteById(long id);

    //boolean delete(T object);
}
