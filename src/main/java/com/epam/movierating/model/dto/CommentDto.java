package com.epam.movierating.model.dto;

import com.epam.movierating.model.Identifiable;
import com.epam.movierating.model.entity.Account;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long movieId;
    private final Account author;
    private final LocalDateTime dateTime;
    private final String text;

    public CommentDto(Long id, Long movieId, Account author, LocalDateTime dateTime, String text) {
        this.id = id;
        this.movieId = movieId;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
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
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movieId, that.movieId) &&
                Objects.equals(author, that.author) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (movieId != null ? movieId.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", author=" + author +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
