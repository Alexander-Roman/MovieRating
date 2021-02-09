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

public class UnblockEditorCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";

    private AccountService accountService;
    private HttpServletRequest request;
    private ServletContext servletContext;
    private UnblockEditorCommand unblockEditorCommand;

    @BeforeMethod
    public void setUp() {
        accountService = Mockito.mock(AccountService.class);
        request = Mockito.mock(HttpServletRequest.class);
        servletContext = Mockito.mock(ServletContext.class);
        unblockEditorCommand = new UnblockEditorCommand(accountService);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        CommandResult commandResult = unblockEditorCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldUnblockEditor() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        unblockEditorCommand.execute(request);
        //then
        verify(accountService, times(1)).unblockEditorById(VALID_ID);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        unblockEditorCommand.execute(request);
        //then
    }
}
