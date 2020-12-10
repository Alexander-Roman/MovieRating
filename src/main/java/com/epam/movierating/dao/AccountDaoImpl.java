package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.entity.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM accounts";
    private static final String SQL_SELECT_PAGE = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts ORDER BY user_name LIMIT ? OFFSET ?;";
    private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts WHERE user_name=? and password=SHA1(?);";

    public AccountDaoImpl(Connection connection, RowMapper<Account> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public Optional<Account> findAccountByUsernameAndPassword(String username, String password) throws DaoException {
        return selectSingle(SQL_SELECT_BY_USERNAME_AND_PASSWORD, username, password);
    }

    @Override
    public long getAccountsAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(0L);
    }

    @Override
    public List<Account> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public long save(Account object) {
        return 0L;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> find(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(long id) {

    }
}
