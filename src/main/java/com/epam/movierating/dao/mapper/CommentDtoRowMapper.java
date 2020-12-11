package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentDtoRowMapper implements RowMapper<CommentDto> {

    private static final String COMMENT_ID_LABEL = "comment_id";
    private static final String MOVIE_ID_LABEL = "movie_id";
    private static final String ACCOUNT_ID_LABEL = "account_id";
    private static final String DATE_TIME_LABEL = "date_time";
    private static final String TEXT_LABEL = "text";

    private final RowMapper<Account> accountRowMapper;

    public CommentDtoRowMapper(RowMapper<Account> accountRowMapper) {
        this.accountRowMapper = accountRowMapper;
    }

    @Override
    public CommentDto map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong(COMMENT_ID_LABEL);
            Long movieId = resultSet.getLong(MOVIE_ID_LABEL);
            Account account = accountRowMapper.map(resultSet);
            LocalDateTime dateTime = resultSet.getObject(DATE_TIME_LABEL, LocalDateTime.class);
            String text = resultSet.getString(TEXT_LABEL);
            return new CommentDto(id, movieId, account, dateTime, text);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
