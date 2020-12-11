package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.model.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    private static final String MOVIE_ID_LABEL = "movie_id";
    private static final String TITLE_LABEL = "title";
    private static final String DIRECTOR_LABEL = "director";
    private static final String RELEASE_YEAR_LABEL = "release_year";
    private static final String SYNOPSIS_LABEL = "synopsis";
    private static final String POSTER_PATH_LABEL = "poster_path";
    private static final String RATING_LABEL = "rating";

    @Override
    public Movie map(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(MOVIE_ID_LABEL);
            String title = resultSet.getString(TITLE_LABEL);
            String director = resultSet.getString(DIRECTOR_LABEL);
            int releaseYear = resultSet.getInt(RELEASE_YEAR_LABEL);
            String synopsis = resultSet.getString(SYNOPSIS_LABEL);
            String posterPath = resultSet.getString(POSTER_PATH_LABEL);
            Double rating = (Double) resultSet.getObject(RATING_LABEL);
            return new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
