package com.epam.movierating.dao.mapper;

import com.epam.movierating.model.dto.UserRatingDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRatingDtoRowMapper implements RowMapper<UserRatingDto> {

    private static final String RATE_ID_LABEL = "rate_id";
    private static final String MOVIE_ID_LABEL = "movie_id";
    private static final String ACCOUNT_ID_LABEL = "account_id";
    private static final String ASSESSMENT_LABEL = "assessment";


    @Override
    public UserRatingDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(RATE_ID_LABEL);
        Long assessedId = resultSet.getLong(MOVIE_ID_LABEL);
        Long assessorId = resultSet.getLong(ACCOUNT_ID_LABEL);
        int assessment = resultSet.getInt(ASSESSMENT_LABEL);
        return new UserRatingDto(id, assessedId, assessorId, assessment);
    }
}
