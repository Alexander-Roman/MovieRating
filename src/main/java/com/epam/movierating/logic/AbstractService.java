package com.epam.movierating.logic;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;

public abstract class AbstractService {

    private final DaoConnectionManagerFactory factory;

    public AbstractService(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    protected DaoConnectionManager createDaoConnectionManager() throws ServiceException {
        try {
            return factory.create();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
