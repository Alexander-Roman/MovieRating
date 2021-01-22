package com.epam.movierating.logic;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;
    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "password";
    private static final int MAX_USERNAME_LENGTH = 45;
    private static final Account ACCOUNT = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
    private static final int VALID_ITEMS_PER_PAGE = 8;
    private static final int INVALID_ITEMS_PER_PAGE = 0;
    private static final int VALID_PAGE = 8;
    private static final int INVALID_PAGE = 0;
    private static final long ACCOUNTS_AMOUNT = 32L;

    private DaoConnectionManagerFactory factory;
    private AccountServiceImpl accountService;
    private DaoConnectionManager daoConnectionManager;
    private AccountDao accountDao;

    @BeforeMethod
    public void setUp() throws DaoException {
        factory = Mockito.mock(DaoConnectionManagerFactory.class);
        accountService = new AccountServiceImpl(factory);
        daoConnectionManager = Mockito.mock(DaoConnectionManager.class);
        accountDao = Mockito.mock(AccountDao.class);

        when(factory.create()).thenReturn(daoConnectionManager);
        when(daoConnectionManager.createAccountDao()).thenReturn(accountDao);
        when(accountDao.findAccountByUsernameAndPassword(VALID_USERNAME, VALID_PASSWORD)).thenReturn(Optional.of(ACCOUNT));
        when(accountDao.getAccountsAmount()).thenReturn(ACCOUNTS_AMOUNT);
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(ACCOUNT));
    }

    @Test
    public void testAuthenticateShouldFindAccountByUsernameAndPassword() throws ServiceException {
        //given
        //when
        Optional<Account> actual = accountService.authenticate(VALID_USERNAME, VALID_PASSWORD);
        //then
        Optional<Account> expected = Optional.of(ACCOUNT);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class, dataProvider = "invalidCredentialParametersProvider")
    public void testAuthenticateShouldThrowExceptionWhenCredentialParametersInvalid(String username, String password) throws ServiceException {
        //given
        //when
        accountService.authenticate(username, password);
        //then
    }

    @Test(dataProvider = "ValidPaginationParametersProvider")
    public void testGetNumberOfPagesShouldReturnCorrectValue(long numberOfItems, int itemsPerPage, int numberOfPagesExpected) throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.getAccountsAmount()).thenReturn(numberOfItems);
        int actual = accountService.getNumberOfPages(itemsPerPage);
        //then
        Assert.assertEquals(actual, numberOfPagesExpected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetNumberOfPagesShouldThrowExceptionWhenItemsPerPageValueInvalid() throws ServiceException {
        //given
        //when
        accountService.getNumberOfPages(INVALID_ITEMS_PER_PAGE);
        //then
    }

    @Test
    public void testGetPageShouldFindBatchOfItems() throws ServiceException, DaoException {
        //given
        //when
        accountService.getPage(VALID_PAGE, VALID_ITEMS_PER_PAGE);
        //then
        verify(accountDao, times(1)).findBatch(anyInt(), anyInt());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPageShouldThrowExceptionWhenPageParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.getPage(INVALID_PAGE, VALID_ITEMS_PER_PAGE);
        //then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPageShouldThrowExceptionWhenItemsPerPageParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.getPage(VALID_PAGE, INVALID_ITEMS_PER_PAGE);
        //then
    }

    @Test
    public void testBlockUserByIdShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account userUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userUnblocked));
        accountService.blockUserById(VALID_ID);
        //then
        Account userBlocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, true);
        verify(accountDao, times(1)).save(userBlocked);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBlockUserByIdShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.blockUserById(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testBlockUserByIdShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.blockUserById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testBlockUserByIdShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account userBlocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userBlocked));
        accountService.blockUserById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testBlockUserByIdShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.ADMIN, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.blockUserById(VALID_ID);
        //then
    }

    @Test
    public void testBlockEditorByIdShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account editorUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorUnblocked));
        accountService.blockEditorById(VALID_ID);
        //then
        Account editorBlocked = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, true);
        verify(accountDao, times(1)).save(editorBlocked);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBlockEditorByIdShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.blockEditorById(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testBlockEditorByIdShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.blockEditorById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testBlockEditorByIdShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account editorBlocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorBlocked));
        accountService.blockEditorById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testBlockEditorByIdShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.ADMIN, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.blockEditorById(VALID_ID);
        //then
    }

    @Test
    public void testUnblockUserByIdShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account userBlocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userBlocked));
        accountService.unblockUserById(VALID_ID);
        //then
        Account userUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        verify(accountDao, times(1)).save(userUnblocked);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUnblockUserByIdShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.unblockUserById(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testUnblockUserByIdShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.unblockUserById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUnblockUserByIdShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account userUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userUnblocked));
        accountService.unblockUserById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUnblockUserByIdShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.unblockUserById(VALID_ID);
        //then
    }

    @Test
    public void testUnblockEditorByIdShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account editorBlocked = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorBlocked));
        accountService.unblockEditorById(VALID_ID);
        //then
        Account editorUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, false);
        verify(accountDao, times(1)).save(editorUnblocked);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUnblockEditorByIdShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.unblockEditorById(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testUnblockEditorByIdShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.unblockEditorById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUnblockEditorByIdShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account editorUnblocked = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorUnblocked));
        accountService.unblockEditorById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUnblockEditorByIdShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.USER, true);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.unblockEditorById(VALID_ID);
        //then
    }

    @Test
    public void testPromoteUserToEditorShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account userAccount = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userAccount));
        accountService.promoteUserToEditor(VALID_ID);
        //then
        Account editorAccount = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, false);
        verify(accountDao, times(1)).save(editorAccount);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPromoteUserToEditorShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.promoteUserToEditor(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testPromoteUserToEditorShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.promoteUserToEditor(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testPromoteUserToEditorShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account editorAccount = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorAccount));
        accountService.promoteUserToEditor(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testPromoteUserToEditorShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.ADMIN, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.promoteUserToEditor(VALID_ID);
        //then
    }

    @Test
    public void testDemoteEditorToUserShouldSaveChanges() throws ServiceException, DaoException {
        //given
        Account editorAccount = new Account(VALID_ID, VALID_USERNAME, Role.EDITOR, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(editorAccount));
        accountService.demoteEditorToUser(VALID_ID);
        //then
        Account userAccount = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        verify(accountDao, times(1)).save(userAccount);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDemoteEditorToUserShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        accountService.demoteEditorToUser(INVALID_ID);
        //then
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testDemoteEditorToUserShouldThrowExceptionWhenAccountNotFound() throws ServiceException, DaoException {
        //given
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.empty());
        accountService.demoteEditorToUser(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testDemoteEditorToUserShouldThrowExceptionWhenNotingToChange() throws ServiceException, DaoException {
        //given
        Account userAccount = new Account(VALID_ID, VALID_USERNAME, Role.USER, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(userAccount));
        accountService.demoteEditorToUser(VALID_ID);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testDemoteEditorToUserShouldThrowExceptionWhenRoleUnexpected() throws ServiceException, DaoException {
        //given
        Account differentRoleAccount = new Account(VALID_ID, VALID_USERNAME, Role.ADMIN, false);
        //when
        when(accountDao.find(VALID_ID)).thenReturn(Optional.of(differentRoleAccount));
        accountService.demoteEditorToUser(VALID_ID);
        //then
    }

    @DataProvider(name = "invalidCredentialParametersProvider")
    public Object[][] provideInvalidCredentialParameters() {
        StringBuilder usernameStringBuilder = new StringBuilder("The length of the string exceeds " + MAX_USERNAME_LENGTH + " characters:");
        for (int i = 0; i <= MAX_USERNAME_LENGTH; i++) {
            usernameStringBuilder.append(".");
        }
        String usernameLengthExceeded = usernameStringBuilder.toString();

        return new String[][]{
                {null, VALID_PASSWORD},
                {"", VALID_PASSWORD},
                {usernameLengthExceeded, VALID_PASSWORD},
                {VALID_USERNAME, null},
                {VALID_USERNAME, ""}
        };
    }

    @DataProvider(name = "ValidPaginationParametersProvider")
    public Object[][] provideValidPaginationParameters() {
        return new Number[][]{
                {0L, 8, 1},
                {7L, 8, 1},
                {8L, 8, 1},
                {9L, 8, 2},
                {99L, 100, 1},
                {100L, 99, 2}
        };
    }
}
