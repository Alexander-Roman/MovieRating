package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.UserRating;

import java.sql.ResultSet;
import java.util.Map;

public class UserRatingRowMapper implements RowMapper<UserRating> {

    @Override
    public UserRating map(ResultSet resultSet) {
        return null;
    }

    @Override
    public Map<String, String> unmap(UserRating object) {
        return null;
    }
}
