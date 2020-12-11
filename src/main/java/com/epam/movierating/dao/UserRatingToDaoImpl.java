package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.UserRatingTo;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserRatingToDaoImpl extends AbstractDao<UserRatingTo> implements UserRatingToDao {

    private static final String SQL_SELECT_BY_ACCOUNT_AND_MOVIE = "SELECT rate_id, movie_id, account_id, assessment FROM user_ratings " +
            "WHERE movie_id = ? AND account_id = ?;";

    public UserRatingToDaoImpl(Connection connection, RowMapper<UserRatingTo> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public Optional<UserRatingTo> getByAssessorAndAssessed(Long movieId, Long accountId) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ACCOUNT_AND_MOVIE, movieId, accountId);
    }

    @Override
    public long save(UserRatingTo object) throws DaoException {
        return 0;
    }

    @Override
    public List<UserRatingTo> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<UserRatingTo> find(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(long id) throws DaoException {

    }
}
