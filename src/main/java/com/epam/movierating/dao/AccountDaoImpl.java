package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.AccountRowMapper;
import com.epam.movierating.entity.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts WHERE user_name=? and password=SHA1(?);";

    public AccountDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Account> findAccountByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForFirstResult(SQL_SELECT_BY_USERNAME_AND_PASSWORD, new AccountRowMapper(), username, password);
    }

    @Override
    public long save(Account object) throws DaoException {
        return 0;
    }

    @Override
    public List<Account> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Account> find(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(long id) throws DaoException {

    }
}
