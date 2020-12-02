package com.epam.movierating.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyConnectionFactory {

    private static final String DATABASE_PROPERTIES_FILE = "property/database.properties";
    private static final String URL_KEY = "url";
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private static ProxyConnectionFactory instance = null;
    private static boolean isReady = false;

    private final Properties properties;
    private final String databaseUrl;

    private ProxyConnectionFactory() throws ConnectionPoolException {
        properties = new Properties();
        Class<ProxyConnectionFactory> clazz = ProxyConnectionFactory.class;
        ClassLoader classLoader = clazz.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(DATABASE_PROPERTIES_FILE);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ConnectionPoolException(e);
        }
        databaseUrl = properties.getProperty(URL_KEY);
    }

    //package-private
    static ProxyConnectionFactory getInstance() throws ConnectionPoolException {
        if (!isReady) {
            INSTANCE_LOCK.lock();
            try {
                if (!isReady) {
                    instance = new ProxyConnectionFactory();
                    isReady = true;
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }

    //package-private
    ProxyConnection create() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(databaseUrl, properties);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
        return new ProxyConnection(connection);
    }
}
