package com.epam.movierating.model;

import java.time.LocalDateTime;

public class CommentTo extends Identifiable {

    private final Long movieId;
    private final Account author;
    private final LocalDateTime dateTime;
    private final String text;

    public CommentTo(Long id, Long movieId, Account author, LocalDateTime dateTime, String text) {
        super(id);
        this.movieId = movieId;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
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
}
