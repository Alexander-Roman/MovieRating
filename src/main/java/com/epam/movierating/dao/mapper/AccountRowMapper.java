package com.epam.movierating.dao.mapper;

import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    private static final String ACCOUNT_ID_LABEL = "account_id";
    private static final String USER_NAME_LABEL = "user_name";
    private static final String ROLE_LABEL = "role";
    private static final String BLOCKED_LABEL = "blocked";

    @Override
    public Account map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ACCOUNT_ID_LABEL);
        String userName = resultSet.getString(USER_NAME_LABEL);
        String roleValue = resultSet.getString(ROLE_LABEL);
        Role role = Role.valueOf(roleValue);
        boolean blocked = resultSet.getBoolean(BLOCKED_LABEL);
        return new Account(id, userName, role, blocked);
    }
}
