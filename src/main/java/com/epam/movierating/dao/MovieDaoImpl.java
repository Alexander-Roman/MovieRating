package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.Movie;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM movies";
    private static final String SQL_SELECT_ALL = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating FROM movies;";
    private static final String SQL_SELECT_PAGE = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating " +
            "FROM movies ORDER BY rating DESC LIMIT ? OFFSET ?;";
    private static final String SQL_SELECT_BY_ID = "SELECT movie_id, title, director, release_year, synopsis, poster_path, rating " +
            "FROM movies WHERE movie_id = ?;";
    private static final String SQL_INSERT_NEW_MOVIE = "INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating) " +
            "VALUES (?, ?, ?, ?, ?, null);";
    private static final String SQL_UPDATE_MOVIE = "UPDATE movies " +
            "SET title = ?, director = ?, release_year = ?, synopsis = ?, poster_path = ?, rating = " +
            "(SELECT AVG(assessment) FROM user_ratings WHERE user_ratings.movie_id = movies.movie_id HAVING COUNT(movie_id) > 3) " +
            "WHERE movie_id = ?;";

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
    public long save(Movie movie) throws DaoException {
        Long id = movie.getId();
        String title = movie.getTitle();
        String director = movie.getDirector();
        Integer releaseYear = movie.getReleaseYear();
        String synopsis = movie.getSynopsis();
        String posterPath = movie.getPosterPath();
        if (id == null) {
            Optional<Long> result = saveSingle(SQL_INSERT_NEW_MOVIE, title, director, releaseYear, synopsis, posterPath);
            if (result.isPresent()) {
                return result.get();
            }
        } else {
            saveSingle(SQL_UPDATE_MOVIE, title, director, releaseYear, synopsis, posterPath, id);
            return id;
        }
        throw new DaoException("Unacceptable query result!");
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Movie> find(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public void delete(long id) {

    }
}
