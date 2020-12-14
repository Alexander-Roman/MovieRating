package com.epam.movierating.logic;

import com.epam.movierating.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> authenticate(String username, String password) throws ServiceException;

    int getNumberOfPages(int itemsPerPage) throws ServiceException;

    List<Account> getPage(int page, int itemsPerPage) throws ServiceException;

    void blockUserById(long id) throws ServiceException;

    void unblockUserById(long id) throws ServiceException;

    void blockEditorById(long id) throws ServiceException;

    void unblockEditorById(long id) throws ServiceException;

    void promoteUserToEditor(long id) throws ServiceException;

    void demoteEditorToUser(long id) throws ServiceException;
}
