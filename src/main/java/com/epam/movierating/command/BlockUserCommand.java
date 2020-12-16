package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {

    private static final String USER_LIST_COMMAND_PATH = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.USER_LIST;
    private final AccountService accountService;

    public BlockUserCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        accountService.blockUserById(id);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + USER_LIST_COMMAND_PATH);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "accountService=" + accountService +
                '}';
    }
}
