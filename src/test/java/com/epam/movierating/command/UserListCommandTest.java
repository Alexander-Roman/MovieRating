package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.NotFoundException;
import com.epam.movierating.logic.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UserListCommandTest {

    private static final String VALID_PAGE_PARAM = "1";
    private static final int VALID_PAGE = 1;
    private static final int NUMBER_OF_PAGES = 1;
    private static final String PAGE_NOT_EXIST_PARAM = "2";
    private static final String INVALID_PAGE_PARAM = "page";

    private AccountService accountService;
    private HttpServletRequest request;
    private UserListCommand userListCommand;

    @BeforeMethod
    public void setUp() {
        accountService = Mockito.mock(AccountService.class);
        request = Mockito.mock(HttpServletRequest.class);
        userListCommand = new UserListCommand(accountService);
    }

    @Test
    private void testExecuteShouldSetDefaultPageIfPageParameterNotDefined() throws ServiceException {
        //given
        normalExecuteScenario();
        when(request.getParameter(Parameter.PAGE)).thenReturn(null);
        //when
        userListCommand.execute(request);
        //then
        verify(accountService, times(1)).getPage(anyInt(), anyInt());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    private void testExecuteShouldThrowExceptionWhenPageParameterInvalid() throws ServiceException {
        //given
        normalExecuteScenario();
        when(request.getParameter(Parameter.PAGE)).thenReturn(INVALID_PAGE_PARAM);
        //when
        userListCommand.execute(request);
        //then
    }

    @Test
    private void testExecuteShouldGetNumberOfPages() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(accountService, times(1)).getNumberOfPages(anyInt());
    }

    @Test(expectedExceptions = NotFoundException.class)
    private void testExecuteShouldThrowExceptionWhenPageIsNotExist() throws ServiceException {
        //given
        normalExecuteScenario();
        when(request.getParameter(Parameter.PAGE)).thenReturn(PAGE_NOT_EXIST_PARAM);
        //when
        userListCommand.execute(request);
        //then
    }

    @Test
    private void testExecuteShouldGetUsersList() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(accountService, times(1)).getPage(eq(VALID_PAGE), anyInt());
    }

    @Test
    private void testExecuteShouldSetItemsPerPageAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(eq(Attribute.ITEMS_PER_PAGE), anyInt());
    }

    @Test
    private void testExecuteShouldSetNumberOfPagesAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.NUMBER_OF_PAGES, NUMBER_OF_PAGES);
    }

    @Test
    private void testExecuteShouldSetPageAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.PAGE, VALID_PAGE);
    }

    @Test
    private void testExecuteShouldSetUsersAttribute() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        userListCommand.execute(request);
        //then
        verify(request, times(1)).setAttribute(Attribute.USERS, Collections.emptyList());
    }

    @Test
    private void testExecuteShouldReturnForwardCommandResult() throws ServiceException {
        //given
        normalExecuteScenario();
        //when
        CommandResult commandResult = userListCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertFalse(actual);
    }

    private void normalExecuteScenario() throws ServiceException {
        when(request.getParameter(Parameter.PAGE)).thenReturn(VALID_PAGE_PARAM);
        when(accountService.getNumberOfPages(anyInt())).thenReturn(NUMBER_OF_PAGES);
        when(accountService.getPage(anyInt(), anyInt())).thenReturn(Collections.emptyList());
    }
}
