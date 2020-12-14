package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class DemoteEditorCommand implements Command {

    private static final String CONTROLLER_COMMAND_USERS = "/controller?command=users";
    private final AccountService accountService;

    public DemoteEditorCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        accountService.demoteEditorToUser(id);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + CONTROLLER_COMMAND_USERS);
    }
}
