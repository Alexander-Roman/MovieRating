package com.epam.movierating.logic;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.entity.Account;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private final DaoConnectionManagerFactory factory;

    public LoginServiceImpl() {
        factory = new DaoConnectionManagerFactory();
    }

    public LoginServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Account> authenticate(String username, String password) throws ServiceException {
        if (username == null || password == null) {
            return Optional.empty();
        }
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            return accountDao.findAccountByUsernameAndPassword(username, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
