package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.MovieRowMapper;
import com.epam.movierating.entity.Movie;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    private static final String TABLE_NAME = "movies";
    private static final String SQL_SELECT_ALL = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating FROM movies;";
    private static final String SQL_SELECT_PAGE = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating " +
            "FROM movies ORDER BY rating DESC LIMIT ? OFFSET ?;";

    public MovieDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Movie> findSetSorted(int amount, int from) throws DaoException {
        int offset = from - 1;
        return execute(SQL_SELECT_PAGE, new MovieRowMapper(), amount, offset);
    }

    @Override
    public long save(Movie object) throws DaoException {
        return 0;
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        return execute(SQL_SELECT_ALL, new MovieRowMapper());
    }

    @Override
    public Optional<Movie> find(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(long id) throws DaoException {

    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
