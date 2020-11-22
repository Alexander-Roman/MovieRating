package com.epam.movierating.entity;

import java.util.Objects;

public final class Movie {

    private final long movieId;
    private final String title;
    private final String director;
    private final int releaseYear;

    public Movie(long movieId, String title, String director, int releaseYear) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return movieId == movie.movieId &&
                releaseYear == movie.releaseYear &&
                Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + releaseYear;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
