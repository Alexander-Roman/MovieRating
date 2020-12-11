package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.dto.UserRatingDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserRatingDtoDaoImpl extends AbstractDao<UserRatingDto> implements UserRatingDtoDao {

    private static final String SQL_SELECT_BY_ACCOUNT_AND_MOVIE = "SELECT rate_id, movie_id, account_id, assessment FROM user_ratings " +
            "WHERE movie_id = ? AND account_id = ?;";
    private static final String SQL_INSERT_USER_RATING = "INSERT INTO user_ratings (movie_id, account_id, assessment) " +
            "VALUES (?, ?, ?);";
    private static final String SQL_UPDATE_USER_RATING = "UPDATE user_ratings " +
            "SET movie_id = ?, account_id = ?, assessment = ? " +
            "WHERE rate_id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT rate_id, movie_id, account_id, assessment FROM user_ratings;";
    private static final String SQL_SELECT_BY_ID = "SELECT rate_id, movie_id, account_id, assessment " +
            "FROM user_ratings WHERE rate_id = ?;";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM user_ratings WHERE rate_id = ?;";

    public UserRatingDtoDaoImpl(Connection connection, RowMapper<UserRatingDto> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public Optional<UserRatingDto> getByAssessorAndAssessed(Long movieId, Long accountId) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ACCOUNT_AND_MOVIE, movieId, accountId);
    }

    @Override
    public long save(UserRatingDto userRatingDto) throws DaoException {
        Long id = userRatingDto.getId();
        Long movieId = userRatingDto.getAssessedId();
        Long accountId = userRatingDto.getAssessorId();
        Integer assessment = userRatingDto.getAssessment();
        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_USER_RATING, movieId, accountId, assessment);
            if (result.isPresent()) {
                return result.get();
            }
        } else {
            updateSingle(SQL_UPDATE_USER_RATING, movieId, accountId, assessment, id);
            return id;
        }
        throw new DaoException("Unacceptable query result!");
    }

    @Override
    public List<UserRatingDto> findAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<UserRatingDto> find(long id) throws DaoException {
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
