package com.epam.movierating.model.entity;

import com.epam.movierating.model.Identifiable;

import java.util.Objects;

public final class Movie implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String title;
    private final String director;
    private final Integer releaseYear;
    private final String synopsis;
    private final String posterPath;
    private final Double rating;

    public Movie(Long id, String title, String director, Integer releaseYear, String synopsis, String posterPath, Double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.synopsis = synopsis;
        this.posterPath = posterPath;
        this.rating = rating;
    }

    @Override
    public Long getId() {
        return id;
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
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(releaseYear, movie.releaseYear) &&
                Objects.equals(synopsis, movie.synopsis) &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", synopsis='" + synopsis + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {

        private Long id;
        private String title;
        private String director;
        private Integer releaseYear;
        private String synopsis;
        private String posterPath;
        private Double rating;

        public Builder() {
        }

        public Builder(Movie movie) {
            id = movie.id;
            title = movie.title;
            director = movie.director;
            releaseYear = movie.releaseYear;
            synopsis = movie.synopsis;
            posterPath = movie.posterPath;
            rating = movie.rating;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder setReleaseYear(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder setSynopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public Builder setPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Movie build() {
            return new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
        }
    }
}