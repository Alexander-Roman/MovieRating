package com.epam.movierating.command;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";

    public static Command create(String command) {
        if (command == null) {
            return new EmptyCommand();
        }
        switch (command) {
            case LOGIN_COMMAND:
                return new LoginCommand();
            default:
                throw new IllegalArgumentException(command + " is unknown Command implementation!");
        }
    }
}
