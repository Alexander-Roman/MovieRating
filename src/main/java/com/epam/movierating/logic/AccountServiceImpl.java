package com.epam.movierating.logic;

import com.epam.movierating.dao.AccountDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_PAGE_VALUE = 1;
    private static final int MIN_ITEMS_PER_PAGE = 1;
    private static final int MAX_USERNAME_LENGTH = 45;
    private final DaoConnectionManagerFactory factory;

    public AccountServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Account> authenticate(String username, String password) throws ServiceException {
        if (username == null || username.isEmpty() || username.length() > MAX_USERNAME_LENGTH) {
            throw new ServiceException("Invalid username value: " + username);
        }
        if (password == null || password.isEmpty()) {
            throw new ServiceException("Invalid password value: " + password);
        }
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            return accountDao.findAccountByUsernameAndPassword(username, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws ServiceException {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEMS_PER_PAGE, "Invalid items per page parameter: " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            long numberOfItems = accountDao.getAccountsAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getPage(int page, int itemsPerPage) throws ServiceException {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page parameter: " + page);

        int firstItemNumber = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            return accountDao.findBatch(itemsPerPage, firstItemNumber);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void blockUserById(long id) throws ServiceException {
        setBlockedByRoleAndId(Role.USER, id, true);
    }

    @Override
    public void unblockUserById(long id) throws ServiceException {
        setBlockedByRoleAndId(Role.USER, id, false);
    }

    @Override
    public void blockEditorById(long id) throws ServiceException {
        setBlockedByRoleAndId(Role.EDITOR, id, true);
    }

    @Override
    public void unblockEditorById(long id) throws ServiceException {
        setBlockedByRoleAndId(Role.EDITOR, id, false);
    }

    private void setBlockedByRoleAndId(Role role, long id, boolean blocked) throws ServiceException {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);
        Preconditions.checkArgument(role != null, "Invalid role value: " + role);

        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            Optional<Account> found = accountDao.find(id);
            Account account = found.orElseThrow(() -> new NotFoundException("No account found by id: " + id));

            boolean blockedActual = account.getBlocked();
            if (blockedActual == blocked) {
                throw new ServiceException("Nothing to set!");
            }
            Role roleActual = account.getRole();
            if (!role.equals(roleActual)) {
                throw new ServiceException("Unexpected account role!");
            }
            String userName = account.getUserName();
            String password = account.getPassword();
            Account changed = new Account(id, userName, password, roleActual, blocked);
            accountDao.save(changed);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void promoteUserToEditor(long id) throws ServiceException {
        switchRoleByAccountId(Role.USER, Role.EDITOR, id);
    }

    @Override
    public void demoteEditorToUser(long id) throws ServiceException {
        switchRoleByAccountId(Role.EDITOR, Role.USER, id);
    }

    private void switchRoleByAccountId(Role fromRole, Role toRole, long id) throws ServiceException {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);
        Preconditions.checkArgument(fromRole != null, "Invalid fromRole value: " + fromRole);
        Preconditions.checkArgument(toRole != null, "Invalid toRole value: " + toRole);

        if (fromRole.equals(toRole)) {
            throw new ServiceException("Nothing to change!");
        }

        try (DaoConnectionManager manager = factory.create()) {
            AccountDao accountDao = manager.createAccountDao();
            Optional<Account> found = accountDao.find(id);
            Account account = found.orElseThrow(() -> new NotFoundException("No account found by id: " + id));

            Role roleActual = account.getRole();
            if (!fromRole.equals(roleActual)) {
                throw new ServiceException("Unexpected account role!");
            }
            String userName = account.getUserName();
            String password = account.getPassword();
            Boolean blocked = account.getBlocked();
            Account changed = new Account(id, userName, password, toRole, blocked);
            accountDao.save(changed);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
