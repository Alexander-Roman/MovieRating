package com.epam.movierating.logic;

import com.epam.movierating.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> authenticate(String username, String password) throws ServiceException;

    int getNumberOfPages(int itemsPerPage) throws ServiceException;

    List<Account> getPage(int page, int itemsPerPage) throws ServiceException;
}
