package com.epam.movierating.model.view;

import com.epam.movierating.model.Identifiable;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;

import java.time.LocalDateTime;
import java.util.Objects;

public final class CommentView implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final AccountView author;
    private final LocalDateTime dateTime;
    private final String text;

    public CommentView(Long id, AccountView author, LocalDateTime dateTime, String text) {
        this.id = id;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
    }

    public static CommentView from(CommentDto commentDto) {
        Long id = commentDto.getId();
        Account author = commentDto.getAuthor();
        AccountView accountView = AccountView.from(author);
        LocalDateTime dateTime = commentDto.getDateTime();
        String text = commentDto.getText();
        return new CommentView(id, accountView, dateTime, text);
    }

    @Override
    public Long getId() {
        return id;
    }

    public AccountView getAuthor() {
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
        CommentView that = (CommentView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(author, that.author) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", author=" + author +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
