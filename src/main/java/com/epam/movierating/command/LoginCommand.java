package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Account;
import com.epam.movierating.logic.LoginService;
import com.epam.movierating.logic.LoginServiceImpl;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.view.localization.LocalizationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String MESSAGE_KEY = "command.login.error.message";
    private final LoginService loginService;

    public LoginCommand() {
        loginService = new LoginServiceImpl();
    }

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String username = request.getParameter(Parameter.USERNAME);
        String password = request.getParameter(Parameter.PASSWORD);
        Optional<Account> account = loginService.authenticate(username, password);
        HttpSession session = request.getSession();
        if (account.isPresent()) {
            session.setAttribute(Attribute.ACCOUNT, account.get());
            return CommandResult.redirect(Page.INDEX);
        } else {
            LocalizationManager localization = (LocalizationManager) session.getAttribute(Attribute.LOCALIZATION);
            String message = localization.getMessage(MESSAGE_KEY);
            request.setAttribute(Attribute.MESSAGE, message);
            return CommandResult.forward(Page.LOGIN);
        }
    }
}
