package com.epam.movierating.command;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutCommandTest {


    private final LogoutCommand logoutCommand = new LogoutCommand();
    private HttpServletRequest request;
    private HttpSession session;

    @BeforeMethod
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() {
        //given
        //when
        when(request.getSession()).thenReturn(session);
        CommandResult commandResult = logoutCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldInvalidateSession() {
        //given
        //when
        when(request.getSession()).thenReturn(session);
        logoutCommand.execute(request);
        //then
        verify(session, times(1)).invalidate();
    }
}
