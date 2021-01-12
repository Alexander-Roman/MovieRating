package com.epam.movierating.dao.mapper;

import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class AccountRowMapperTest {

    private static final String ROLE_LABEL = "role";

    private final AccountRowMapper accountRowMapper = new AccountRowMapper();

    @Test
    public void testMapShouldReturnAccountWithNoPassword() throws SQLException {
        //given
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        //when
        when(resultSet.getString(anyString())).thenReturn("value");
        when(resultSet.getString(ROLE_LABEL)).thenReturn("USER");
        Account account = accountRowMapper.map(resultSet);
        //then
        String actual = account.getPassword();
        Assert.assertNull(actual);
    }
}
