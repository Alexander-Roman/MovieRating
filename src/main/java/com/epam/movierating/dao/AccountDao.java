package com.epam.movierating.dao;

import com.epam.movierating.model.entity.Account;

import java.util.List;
import java.util.Optional;

/**
 * Extending DAO interface for managing Accounts
 */
public interface AccountDao extends Dao<Account> {

    /**
     * Finds Account specified by username and password. Returns in Optional, or empty Optional if no matching found.
     * @param username required Account username
     * @param password required Account password
     * @return Optional of Account found, or empty if found nothing
     * @throws DaoException in case of errors
     */
    Optional<Account> findAccountByUsernameAndPassword(String username, String password) throws DaoException;

    /**
     * Returns the number of stored accounts
     * @return all accounts amount
     * @throws DaoException in case of errors
     */
    long getAccountsAmount() throws DaoException;

    /**
     * Returns a specified number of accounts starting from a specified sequence number
     * @param amount maximum number of accounts
     * @param from ordered number of the first
     * @return List of accounts from a given range
     * @throws DaoException in case of errors
     */
    List<Account> findBatch(int amount, int from) throws DaoException;
}
