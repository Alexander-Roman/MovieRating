package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

public class BlockUserCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";

    AccountService accountService;
    HttpServletRequest request;
    ServletContext servletContext;
    BlockUserCommand blockUserCommand;

    @BeforeMethod
    public void setUp() {
        accountService = Mockito.mock(AccountService.class);
        request = Mockito.mock(HttpServletRequest.class);
        servletContext = Mockito.mock(ServletContext.class);
        blockUserCommand = new BlockUserCommand(accountService);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        CommandResult commandResult = blockUserCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldBlockUser() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        blockUserCommand.execute(request);
        //then
        verify(accountService, times(1)).blockUserById(VALID_ID);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        blockUserCommand.execute(request);
        //then
    }
}
