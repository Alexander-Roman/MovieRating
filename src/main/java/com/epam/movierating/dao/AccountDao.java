package com.epam.movierating.dao;

import com.epam.movierating.entity.Account;

import java.util.Optional;

public interface AccountDao extends Dao<Account> {

    Optional<Account> findAccountByUsernameAndPassword(String username, String password) throws DaoException;
}
