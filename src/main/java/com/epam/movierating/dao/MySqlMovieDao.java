package com.epam.movierating.dao;

import com.epam.movierating.entity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMovieDao extends AbstractDao implements MovieDao {

    private static final String SQL_SELECT_ALL_MOVIES = "SELECT * FROM movies";

    public MySqlMovieDao() throws DaoException {
    }

    @Override
    public List<Movie> getAll() throws DaoException {
        List<Movie> movies = new ArrayList<Movie>();

        try {
            PreparedStatement statement = prepareStatement(SQL_SELECT_ALL_MOVIES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long movieId = resultSet.getLong("movie_id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                int releaseYear = resultSet.getInt("release_year");
                String synopsis = resultSet.getString("synopsis");
                String posterPath = resultSet.getString("poster_path");
                double rating = 9.1; //todo
                Movie movie = new Movie(movieId, title, director, releaseYear, synopsis, posterPath, rating);
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return movies;
    }

}
