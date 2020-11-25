package com.epam.movierating.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Comment {

    private final long commentID;
    private final Movie targetMovie;
    private final Account author;
    private final LocalDateTime dateTime;
    private final String text;

    public Comment(long commentID, Movie targetMovie, Account author, LocalDateTime dateTime, String text) {
        this.commentID = commentID;
        this.targetMovie = targetMovie;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
    }

    public long getCommentID() {
        return commentID;
    }

    public Movie getTargetMovie() {
        return targetMovie;
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
        return commentID == comment.commentID &&
                Objects.equals(targetMovie, comment.targetMovie) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(dateTime, comment.dateTime) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        int result = (int) (commentID ^ (commentID >>> 32));
        result = 31 * result + (targetMovie != null ? targetMovie.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "commentID=" + commentID +
                ", targetMovie=" + targetMovie +
                ", author=" + author +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
