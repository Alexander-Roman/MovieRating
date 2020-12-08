package com.epam.movierating.command;

import com.epam.movierating.command.context.CommandResult;
import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Account;
import com.epam.movierating.logic.LoginService;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String MESSAGE_KEY_WRONG = "command.login.error.wrong";
    private static final String MESSAGE_KEY_BLOCKED = "command.login.error.blocked";
    private final LoginService loginService;

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String username = request.getParameter(Parameter.USERNAME);
        String password = request.getParameter(Parameter.PASSWORD);

        HttpSession session = request.getSession();
        Optional<Account> account = loginService.authenticate(username, password);
        if (!account.isPresent()) {
            request.setAttribute(Attribute.MESSAGE, MESSAGE_KEY_WRONG);
            return CommandResult.forward(Page.LOGIN);
        }
        Account found = account.get();
        if (found.isBlocked()) {
            request.setAttribute(Attribute.MESSAGE, MESSAGE_KEY_BLOCKED);
            return CommandResult.forward(Page.LOGIN);
        }
        session.setAttribute(Attribute.ACCOUNT, found);
        return CommandResult.redirect(Page.INDEX);
    }
}
