package com.epam.movierating.logic;

import com.epam.movierating.entity.Account;

import java.util.Optional;

public interface LoginService {

    Optional<Account> authenticate(String username, String password) throws ServiceException;
}
