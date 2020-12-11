package com.epam.movierating.model;

import java.util.Objects;

public final class Movie extends Identifiable {

    private final String title;
    private final String director;
    private final Integer releaseYear;
    private final String synopsis;
    private final String posterPath;
    private final Double rating;

    public Movie(Long id, String title, String director, Integer releaseYear, String synopsis, String posterPath, Double rating) {
        super(id);
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.synopsis = synopsis;
        this.posterPath = posterPath;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(releaseYear, movie.releaseYear) &&
                Objects.equals(synopsis, movie.synopsis) &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", synopsis='" + synopsis + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", rating=" + rating +
                '}';
    }
}