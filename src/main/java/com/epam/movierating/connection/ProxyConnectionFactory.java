package com.epam.movierating.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProxyConnectionFactory {

    private static final String DATABASE_PROPERTIES_FILE = "property/database.properties";
    private static final String URL_KEY = "url";

    private final Properties properties;
    private final String databaseUrl;

    //package-private
    ProxyConnectionFactory() throws ConnectionPoolException {
        properties = new Properties();
        Class<ProxyConnectionFactory> clazz = ProxyConnectionFactory.class;
        ClassLoader classLoader = clazz.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(DATABASE_PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ConnectionPoolException(e);
        }
        databaseUrl = properties.getProperty(URL_KEY);
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
