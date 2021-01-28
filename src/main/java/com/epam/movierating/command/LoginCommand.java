package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.entity.Account;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN_PAGE_COMMAND_PATH = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.LOGIN_PAGE;
    private final AccountService accountService;

    public LoginCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String username = request.getParameter(Parameter.USERNAME);
        String password = request.getParameter(Parameter.PASSWORD);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();

        Optional<Account> found = accountService.authenticate(username, password);
        if (!found.isPresent()) {
            return CommandResult.redirect(contextPath + LOGIN_PAGE_COMMAND_PATH + "&" + Parameter.AUTHENTICATION + "=" + Result.WRONG_CREDENTIALS);
        }
        Account account = found.get();

        if (account.getBlocked()) {
            return CommandResult.redirect(contextPath + LOGIN_PAGE_COMMAND_PATH + "&" + Parameter.AUTHENTICATION + "=" + Result.BLOCKED);
        }

        HttpSession session = request.getSession();
        session.setAttribute(Attribute.ACCOUNT, account);
        return CommandResult.redirect(Page.INDEX);
    }

    public enum Result {
        WRONG_CREDENTIALS, BLOCKED
    }
}
