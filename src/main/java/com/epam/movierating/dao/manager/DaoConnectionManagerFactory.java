package com.epam.movierating.dao.manager;

import com.epam.movierating.connection.ConnectionPool;

import java.sql.Connection;

public class DaoConnectionManagerFactory {

    public DaoConnectionManager create() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        return new DaoConnectionManagerImpl(connection);
    }
}
