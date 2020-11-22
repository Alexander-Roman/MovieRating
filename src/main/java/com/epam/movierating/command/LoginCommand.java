package com.epam.movierating.command;

import com.epam.movierating.logic.LoginService;
import com.epam.movierating.logic.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final LoginService LOGIN_SERVICE = new LoginServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        boolean isValid = LOGIN_SERVICE.login(login, password);
        if (isValid) {
            return "WEB-INF/view/home.jsp";
        } else {
            request.setAttribute("errorMessage", "Invalid credentials!");
            return "WEB-INF/view/login.jsp";
        }
    }
}
