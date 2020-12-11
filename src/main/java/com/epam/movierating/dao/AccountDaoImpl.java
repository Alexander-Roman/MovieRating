package com.epam.movierating.dao;

import com.epam.movierating.dao.mapper.RowMapper;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    private static final Long NO_ITEMS_RESULT = 0L;
    private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts WHERE user_name=? and password=SHA1(?);";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM accounts";
    private static final String SQL_SELECT_PAGE = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts ORDER BY user_name LIMIT ? OFFSET ?;";
    private static final String SQL_INSERT_ACCOUNT = "INSERT INTO accounts (user_name, password, role, blocked) " +
            "VALUES (?, SHA1(?), ?, ?);";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE accounts " +
            "SET user_name = ?, password = SHA1(?), role = ?, blocked = ? " +
            "WHERE account_id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT account_id, user_name, password, role, blocked FROM accounts;";
    private static final String SQL_SELECT_BY_ID = "SELECT account_id, user_name, password, role, blocked " +
            "FROM accounts WHERE account_id = ?;";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM accounts WHERE account_id = ?;";

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
        return (long) result.orElse(NO_ITEMS_RESULT);
    }

    @Override
    public List<Account> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public long save(Account account) throws DaoException {
        Long id = account.getId();
        String userName = account.getUserName();
        String password = account.getPassword();
        Role role = account.getRole();
        Boolean blocked = account.getBlocked();
        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_ACCOUNT, userName, password, role, blocked);
            if (result.isPresent()) {
                return result.get();
            }
        } else {
            updateSingle(SQL_UPDATE_ACCOUNT, userName, password, role, blocked, id);
            return id;
        }
        throw new DaoException("Unacceptable query result!");
    }

    @Override
    public List<Account> findAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Account> find(long id) throws DaoException {
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
