package com.epam.movierating.logic;

import com.epam.movierating.model.entity.Account;

import java.util.Optional;

/**
 * The interface describes a logic class for operations with Accounts
 */
public interface AccountService extends PaginationService<Account> {

    /**
     * Checks for the presence of registered account in the system by name and password.
     * Returns result as Optional of Account found, or empty Optional if found nothing.
     * @param username Account username
     * @param password Account password
     * @return Optional of result found
     * @throws ServiceException in case of errors
     */
    Optional<Account> authenticate(String username, String password) throws ServiceException;

    /**
     * Blocks USER Account by the specified ID
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void blockUserById(long id) throws ServiceException;

    /**
     * Unblocks USER Account by the specified ID
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void unblockUserById(long id) throws ServiceException;

    /**
     * Blocks EDITOR Account by the specified ID
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void blockEditorById(long id) throws ServiceException;

    /**
     * Unblocks EDITOR Account by the specified ID
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void unblockEditorById(long id) throws ServiceException;

    /**
     * Changes the USER Account Role to EDITOR
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void promoteUserToEditor(long id) throws ServiceException;

    /**
     * Changes the EDITOR Account Role to USER
     * @param id Account ID
     * @throws ServiceException in case of errors
     */
    void demoteEditorToUser(long id) throws ServiceException;
}
