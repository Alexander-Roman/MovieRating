package com.epam.movierating.command;

import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Account;
import com.epam.movierating.logic.LoginService;
import com.epam.movierating.logic.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {

    private final LoginService loginService;

    public LoginCommand() {
        loginService = new LoginServiceImpl();
    }

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String username = request.getParameter(Parameter.USERNAME);
        String password = request.getParameter(Parameter.PASSWORD);


        if (username == null || password == null) {

        }

        Optional<Account> account = loginService.authenticate(username, password);
        if (account.isPresent()) {
            request.getSession()
        }


        if (account.isPresent()) {
            return CommandResult.redirect(Page.INDEX);
        } else {
            request.setAttribute("errorMessage", "Invalid credentials!");
            return CommandResult.forward(Page.LOGIN);
        }
        return CommandResult.forward(Page.LOGIN);
    }
}
