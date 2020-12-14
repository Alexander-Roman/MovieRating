package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {

    private static final String USER_LIST_COMMAND = "/controller?command=users";
    private final AccountService accountService;

    public UnblockUserCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        accountService.unblockUserById(id);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + USER_LIST_COMMAND);
    }
}
