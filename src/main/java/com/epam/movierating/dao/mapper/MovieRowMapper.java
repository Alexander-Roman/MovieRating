package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    /*
    @Override
    public Map<String, String> unmap(Movie object) {
        Long id = object.getId();
        String title = object.getTitle();
        String director = object.getDirector();
        Integer releaseYear = object.getReleaseYear();
        String synopsis = object.getSynopsis();
        String posterPath = object.getPosterPath();
        Double rating = object.getRating();

        Map<String, String> values = new HashMap<>();
        values.put(MOVIE_ID_LABEL, id.toString());
        values.put(TITLE_LABEL, title);
        values.put(DIRECTOR_LABEL, director);
        values.put(RELEASE_YEAR_LABEL, releaseYear.toString());
        values.put(SYNOPSIS_LABEL, synopsis);
        values.put(POSTER_PATH_LABEL, posterPath);
        values.put(RATING_LABEL, rating.toString());
        return values;
    }

     */
}
