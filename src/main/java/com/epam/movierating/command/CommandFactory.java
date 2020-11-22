package com.epam.movierating.command;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGIN_PAGE_COMMAND = "loginPage";
    private static final String LOGIN_PAGE = "login.jsp";

    public static Command create(String command) {
        if (command == null) {
            return new EmptyCommand();
        }
        switch (command) {
            case LOGIN_COMMAND:
                return new LoginCommand();
            case LOGIN_PAGE_COMMAND:
                return new GoToPageCommand(LOGIN_PAGE);
            default:
                throw new IllegalArgumentException(command + " is unknown Command implementation!");
        }
    }
}
