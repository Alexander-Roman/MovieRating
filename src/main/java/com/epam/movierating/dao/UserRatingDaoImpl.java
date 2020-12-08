package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.entity.UserRating;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserRatingDaoImpl extends AbstractDao<UserRating> implements UserRatingDao {

    public UserRatingDaoImpl(Connection connection, RowMapper<UserRating> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public long save(UserRating object) {
        return 0L;
    }

    @Override
    public List<UserRating> findAll() {
        return null;
    }

    @Override
    public Optional<UserRating> find(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(long id) {

    }
}
