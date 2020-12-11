package com.epam.movierating.logic;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.model.entity.Account;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private static final int MIN_PAGE_VALUE = 1;
    private static final int MIN_ITEMS_PER_PAGE = 1;
    private final DaoConnectionManagerFactory factory;

    public AccountServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Account> authenticate(String username, String password) throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            return accountDao.findAccountByUsernameAndPassword(username, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws ServiceException {
        if (itemsPerPage < MIN_ITEMS_PER_PAGE) {
            throw new ServiceException("Invalid items per page parameter: " + itemsPerPage);
        }
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            long numberOfItems = accountDao.getAccountsAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getPage(int page, int itemsPerPage) throws ServiceException {
        if (page < MIN_PAGE_VALUE) {
            throw new ServiceException("Invalid page parameter: " + page);
        }
        int firstItemNumber = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            return accountDao.findBatch(itemsPerPage, firstItemNumber);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
