package com.epam.movierating.dao.mapper;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountRowMapper implements RowMapper<Account> {

    private static final String ACCOUNT_ID_LABEL = "account_id";
    private static final String USER_NAME_LABEL = "user_name";
    private static final String PASSWORD_LABEL = "password";
    private static final String ROLE_LABEL = "role";
    private static final String BLOCKED_LABEL = "blocked";

    @Override
    public Account map(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ACCOUNT_ID_LABEL);
            String userName = resultSet.getString(USER_NAME_LABEL);
            String password = resultSet.getString(PASSWORD_LABEL);
            String roleValue = resultSet.getString(ROLE_LABEL);
            Role role = Role.valueOf(roleValue);
            boolean blocked = resultSet.getBoolean(BLOCKED_LABEL);
            return new Account(id, userName, password, role, blocked);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Map<String, String> unmap(Account object) {
        Long id = object.getId();
        String userName = object.getUserName();
        String password = object.getPassword();
        Role role = object.getRole();
        boolean blocked = object.isBlocked();

        Map<String, String> values = new HashMap<>();
        values.put(ACCOUNT_ID_LABEL, id.toString());
        values.put(USER_NAME_LABEL, userName);
        values.put(PASSWORD_LABEL, password);
        values.put(ROLE_LABEL, role.name());
        values.put(BLOCKED_LABEL, String.valueOf(blocked));
        return values;
    }
}
