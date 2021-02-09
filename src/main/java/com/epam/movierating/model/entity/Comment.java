package com.epam.movierating.model.entity;

import com.epam.movierating.model.Identifiable;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Comment implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Movie movie;
    private final Account author;
    private final LocalDateTime dateTime;
    private final String text;

    public Comment(Long id, Movie movie, Account author, LocalDateTime dateTime, String text) {
        this.id = id;
        this.movie = movie;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Account getAuthor() {
        return author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(movie, comment.movie) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(dateTime, comment.dateTime) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", movie=" + movie +
                ", author=" + author +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
