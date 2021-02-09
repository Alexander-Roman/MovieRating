package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

public class LoginPageCommandTest {

    private static final String AUTHENTICATION_PARAM_WRONG_CREDENTIALS = "WRONG_CREDENTIALS";
    private static final String AUTHENTICATION_PARAM_BLOCKED = "BLOCKED";
    private static final String AUTHENTICATION_PARAM_INVALID = "invalid";

    private final LoginPageCommand loginPageCommand = new LoginPageCommand();
    private HttpServletRequest request;


    @BeforeMethod
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter(Parameter.AUTHENTICATION)).thenReturn(null);
    }

    @Test
    public void testExecuteShouldReturnForwardCommandResult() {
        //given
        //when
        CommandResult commandResult = loginPageCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertFalse(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExecuteShouldThrowExceptionWhenAuthenticationParameterInvalid() {
        //given
        //when
        when(request.getParameter(Parameter.AUTHENTICATION)).thenReturn(AUTHENTICATION_PARAM_INVALID);
        loginPageCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldSetMessageAttributeWhenCredentialsWrong() {
        //given
        //when
        when(request.getParameter(Parameter.AUTHENTICATION)).thenReturn(AUTHENTICATION_PARAM_WRONG_CREDENTIALS);
        loginPageCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(eq(Attribute.MESSAGE), anyString());
    }

    @Test
    public void testExecuteShouldSetMessageAttributeWhenAccountBlocked() {
        //given
        //when
        when(request.getParameter(Parameter.AUTHENTICATION)).thenReturn(AUTHENTICATION_PARAM_BLOCKED);
        loginPageCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(eq(Attribute.MESSAGE), anyString());
    }
}
