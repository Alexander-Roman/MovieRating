package com.epam.movierating.dao;

import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends Dao<Account> {

    Optional<Account> findAccountByUsernameAndPassword(String username, String password) throws DaoException;

    long getAccountsAmount() throws DaoException;

    List<Account> findBatch(int amount, int from) throws DaoException;
}
