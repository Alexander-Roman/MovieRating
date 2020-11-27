package com.epam.movierating.entity;

import java.util.Objects;

public final class Movie {

    private final long movieId;
    private final String title;
    private final String director;
    private final int releaseYear;
    private final String synopsis;
    private final String posterPath;
    private final double rating;

    public Movie(long movieId, String title, String director, int releaseYear, String synopsis, String posterPath, double rating) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.synopsis = synopsis;
        this.posterPath = posterPath;
        this.rating = rating;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getRating() {
        return rating;
    }
}