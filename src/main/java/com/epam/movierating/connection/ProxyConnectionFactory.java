package com.epam.movierating.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProxyConnectionFactory {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("property.database");
    private static final String DATABASE_URL = RESOURCE_BUNDLE.getString("database.url");
    private static final String USER = RESOURCE_BUNDLE.getString("database.user.name");
    private static final String PASSWORD = RESOURCE_BUNDLE.getString("database.user.password");

    //package-private
    static ProxyConnection create() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return new ProxyConnection(connection);
    }
}
