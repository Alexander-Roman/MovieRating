package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.entity.CommentTo;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CommentToDaoImpl extends AbstractDao<CommentTo> implements CommentToDao {

    private static final String SQL_SELECT_BY_MOVIE_ID = "SELECT comments.comment_id, comments.movie_id, comments.date_time, comments.text, " +
            "accounts.account_id, accounts.user_name, accounts.role, accounts.blocked " +
            "FROM comments LEFT JOIN accounts ON comments.account_id = accounts.account_id " +
            "WHERE movie_id = ? ORDER BY comments.date_time;";

    public CommentToDaoImpl(Connection connection, RowMapper<CommentTo> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<CommentTo> getByMovieId(Long movieId) throws DaoException {
        return selectSeveral(SQL_SELECT_BY_MOVIE_ID, movieId);
    }

    @Override
    public long save(CommentTo object) throws DaoException {
        return 0;
    }

    @Override
    public List<CommentTo> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<CommentTo> find(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(long id) throws DaoException {

    }
}
