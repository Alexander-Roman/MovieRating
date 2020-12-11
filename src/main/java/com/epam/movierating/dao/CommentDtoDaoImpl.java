package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentDtoDaoImpl extends AbstractDao<CommentDto> implements CommentDtoDao {

    private static final String SQL_SELECT_BY_MOVIE_ID = "SELECT comments.comment_id, comments.movie_id, comments.date_time, comments.text, " +
            "accounts.account_id, accounts.user_name, accounts.role, accounts.blocked " +
            "FROM comments LEFT JOIN accounts ON comments.account_id = accounts.account_id " +
            "WHERE movie_id = ? ORDER BY comments.date_time;";
    private static final String SQL_INSERT_COMMENT = "INSERT INTO comments (movie_id, account_id, date_time, text) " +
            "VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_COMMENT = "UPDATE comments " +
            "SET movie_id = ?, account_id = ?, date_time = ?, text = ? " +
            "WHERE comment_id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT comments.comment_id, comments.movie_id, comments.date_time, comments.text, " +
            "accounts.account_id, accounts.user_name, accounts.role, accounts.blocked " +
            "FROM comments LEFT JOIN accounts ON comments.account_id = accounts.account_id;";
    private static final String SQL_SELECT_BY_ID = "SELECT comments.comment_id, comments.movie_id, comments.date_time, comments.text, " +
            "accounts.account_id, accounts.user_name, accounts.role, accounts.blocked " +
            "FROM comments LEFT JOIN accounts ON comments.account_id = accounts.account_id " +
            "WHERE comment_id = ?;";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM comments WHERE comment_id = ?;";

    public CommentDtoDaoImpl(Connection connection, RowMapper<CommentDto> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<CommentDto> getByMovieId(Long movieId) throws DaoException {
        return selectSeveral(SQL_SELECT_BY_MOVIE_ID, movieId);
    }

    @Override
    public long save(CommentDto commentDto) throws DaoException {
        Long id = commentDto.getId();
        Long movieId = commentDto.getMovieId();
        Account account = commentDto.getAuthor();
        Long accountId = account.getId();
        LocalDateTime dateTime = commentDto.getDateTime();
        String text = commentDto.getText();
        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_COMMENT, movieId, accountId, dateTime, text);
            if (result.isPresent()) {
                return result.get();
            }
        } else {
            updateSingle(SQL_UPDATE_COMMENT, movieId, accountId, dateTime, text, id);
            return id;
        }
        throw new DaoException("Unacceptable query result!");
    }

    @Override
    public List<CommentDto> findAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<CommentDto> find(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public void delete(long id) throws DaoException {
        updateSingle(SQL_DELETE_BY_ID, id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
