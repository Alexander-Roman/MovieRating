package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.CommentTo;
import com.epam.movierating.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentToRowMapper implements RowMapper<CommentTo> {

    private static final String COMMENT_ID_LABEL = "comment_id";
    private static final String MOVIE_ID_LABEL = "movie_id";
    private static final String ACCOUNT_ID_LABEL = "account_id";
    private static final String DATE_TIME_LABEL = "date_time";
    private static final String TEXT_LABEL = "text";

    private final RowMapper<Account> accountRowMapper;

    public CommentToRowMapper(RowMapper<Account> accountRowMapper) {
        this.accountRowMapper = accountRowMapper;
    }

    @Override
    public CommentTo map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong(COMMENT_ID_LABEL);
            Long movieId = resultSet.getLong(MOVIE_ID_LABEL);
            Account account = accountRowMapper.map(resultSet);
            LocalDateTime dateTime = resultSet.getObject(DATE_TIME_LABEL, LocalDateTime.class);
            String text = resultSet.getString(TEXT_LABEL);
            return new CommentTo(id, movieId, account, dateTime, text);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
