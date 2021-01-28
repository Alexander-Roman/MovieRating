package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class LoginCommandTest {

    private static final Account ACTIVE = new Account(1L, "userName", Role.USER, false);
    private static final Account BLOCKED = new Account(1L, "userName", Role.USER, true);

    private HttpServletRequest request;
    private HttpSession session;
    private AccountService accountService;
    private LoginCommand loginCommand;
    private ServletContext servletContext;

    @BeforeMethod
    public void setUp() throws ServiceException {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        accountService = Mockito.mock(AccountService.class);
        loginCommand = new LoginCommand(accountService);
        servletContext = Mockito.mock(ServletContext.class);

        when(request.getParameter(anyString())).thenReturn("anyString");
        when(accountService.authenticate(anyString(), anyString())).thenReturn(Optional.of(ACTIVE));
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getContextPath()).thenReturn("contextPath");
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResultWhenLoginSuccess() throws ServiceException {
        //given
        //when
        CommandResult commandResult = loginCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldAuthenticate() throws ServiceException {
        //given
        //when
        loginCommand.execute(request);
        //then
        verify(accountService, times(1)).authenticate(anyString(), anyString());
    }

    @Test
    public void testExecuteShouldSetAccountSessionAttributeWhenLoginSuccess() throws ServiceException {
        //given
        //when
        loginCommand.execute(request);
        //then
        verify(session, times(1)).setAttribute(Attribute.ACCOUNT, ACTIVE);
    }

    @Test
    public void testExecuteShouldNotSetAccountWhenBlocked() throws ServiceException {
        //given
        //when
        when(accountService.authenticate(anyString(), anyString())).thenReturn(Optional.of(BLOCKED));
        loginCommand.execute(request);
        //then
        verify(request, never()).setAttribute(eq(Attribute.ACCOUNT), any());
    }

    @Test
    public void testExecuteShouldNotSetAccountLoginNotSuccess() throws ServiceException {
        //given
        //when
        when(accountService.authenticate(anyString(), anyString())).thenReturn(Optional.empty());
        loginCommand.execute(request);
        //then
        verify(request, never()).setAttribute(eq(Attribute.ACCOUNT), any());
    }
}
