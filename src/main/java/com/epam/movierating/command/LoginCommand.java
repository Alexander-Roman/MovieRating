package com.epam.movierating.command;

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

    private static final String MESSAGE_KEY = "command.login.error.message";
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
        if (account.isPresent()) {
            session.setAttribute(Attribute.ACCOUNT, account.get());
            return CommandResult.redirect(Page.INDEX);
        } else {
            request.setAttribute(Attribute.MESSAGE, MESSAGE_KEY);
            return CommandResult.forward(Page.LOGIN);
        }
    }
}
