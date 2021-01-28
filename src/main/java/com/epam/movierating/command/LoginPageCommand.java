package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {

    private static final String MESSAGE_KEY_WRONG = "command.login.error.wrong";
    private static final String MESSAGE_KEY_BLOCKED = "command.login.error.blocked";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String loginResultParameter = request.getParameter(Parameter.AUTHENTICATION);

        if (loginResultParameter == null) {
            return CommandResult.forward(Page.LOGIN);
        }

        LoginCommand.Result loginResult = LoginCommand.Result.valueOf(loginResultParameter);
        switch (loginResult) {
            case WRONG_CREDENTIALS:
                request.setAttribute(Attribute.MESSAGE, MESSAGE_KEY_WRONG);
                break;
            case BLOCKED:
                request.setAttribute(Attribute.MESSAGE, MESSAGE_KEY_BLOCKED);
                break;
            default:
                throw new IllegalArgumentException("Unknown login command result:" + loginResult);
        }
        return CommandResult.forward(Page.LOGIN);
    }
}
