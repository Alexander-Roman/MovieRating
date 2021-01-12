package com.epam.movierating.connection;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ProxyConnectionTest {

    private Connection connection;
    private ConnectionPool connectionPool;
    private ProxyConnection proxyConnection;

    @BeforeMethod
    public void setUp() throws SQLException {
        connection = Mockito.mock(Connection.class);
        connectionPool = Mockito.mock(ConnectionPool.class);
        proxyConnection = new ProxyConnection(connection, connectionPool);

        when(connection.getAutoCommit()).thenReturn(true);
    }

    @Test
    public void testCloseShouldNotCloseOriginalConnection() throws SQLException {
        //given
        //when
        proxyConnection.close();
        //then
        verify(connection, never()).close();
    }

    @Test
    public void testCloseShouldSetAutoCommitTrue() throws SQLException {
        //given
        //when
        when(connection.getAutoCommit()).thenReturn(false);
        proxyConnection.close();
        //then
        verify(connection, times(1)).setAutoCommit(true);
    }

    @Test
    public void testCloseShouldReleaseProxyConnectionToPool() throws SQLException {
        //given
        //when
        proxyConnection.close();
        //then
        verify(connectionPool, times(1)).releaseConnection(proxyConnection);
    }

    @Test
    public void testCloseConnectionShouldCloseOriginalConnection() throws SQLException {
        //given
        //when
        proxyConnection.closeConnection();
        //then
        verify(connection, times(1)).close();
    }
}
