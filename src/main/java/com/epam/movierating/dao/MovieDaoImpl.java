package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.entity.Movie;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM movies";
    private static final String SQL_SELECT_ALL = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating FROM movies;";
    private static final String SQL_SELECT_PAGE = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating " +
            "FROM movies ORDER BY rating DESC LIMIT ? OFFSET ?;";

    public MovieDaoImpl(Connection connection, RowMapper<Movie> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<Movie> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public long getMoviesAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(0L);
    }

    @Override
    public long save(Movie object) {
        return 0;
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Movie> find(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(long id) {

    }
}
