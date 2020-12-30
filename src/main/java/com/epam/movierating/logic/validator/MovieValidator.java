package com.epam.movierating.logic.validator;

import com.epam.movierating.model.entity.Movie;

public class MovieValidator implements Validator<Movie> {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MAX_TITLE_LENGTH = 255;
    private static final int MAX_DIRECTOR_LENGTH = 45;
    private static final int FIRST_MOVIE_YEAR = 1895;
    private static final int MAX_SYNOPSIS_LENGTH = 1000;
    private static final int MAX_POSTER_PATH_LENGTH = 255;
    private static final double MIN_RATING = 0.0;
    private static final double MAX_RATING = 10.0;

    @Override
    public boolean isValid(Movie movie) {
        Long id = movie.getId();
        String title = movie.getTitle();
        String director = movie.getDirector();
        Integer releaseYear = movie.getReleaseYear();
        String synopsis = movie.getSynopsis();
        String posterPath = movie.getPosterPath();
        Double rating = movie.getRating();

        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }
        if (title == null || title.trim().isEmpty() || title.length() > MAX_TITLE_LENGTH) {
            return false;
        }
        if (director != null && (director.trim().isEmpty() || director.length() > MAX_DIRECTOR_LENGTH)) {
            return false;
        }
        if (releaseYear != null && releaseYear < FIRST_MOVIE_YEAR) {
            return false;
        }
        if (synopsis != null && (synopsis.trim().isEmpty() || synopsis.length() > MAX_SYNOPSIS_LENGTH)) {
            return false;
        }
        if (posterPath != null && (posterPath.trim().isEmpty() || posterPath.length() > MAX_POSTER_PATH_LENGTH)) {
            return false;
        }
        return rating == null || (MIN_RATING <= rating && rating <= MAX_RATING);
    }
}
