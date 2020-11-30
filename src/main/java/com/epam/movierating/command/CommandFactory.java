package com.epam.movierating.command;

import com.epam.movierating.constant.Page;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGIN_PAGE_COMMAND = "loginPage";
    private static final String HOME_PAGE_COMMAND = "homePage";
    private static final String MOVIE_PAGE_COMMAND = "moviePage";
    private static final String LOCALE_COMMAND = "locale";

    public static Command create(String command) {
        if (command == null) {
            return new HomePageCommand();
        }
        switch (command) {
            case LOGIN_COMMAND:
                return new LoginCommand();
            case HOME_PAGE_COMMAND:
                return new HomePageCommand();
            case LOGIN_PAGE_COMMAND:
                return new GoToPageCommand(Page.LOGIN);
            case MOVIE_PAGE_COMMAND:
                return new MoviePageCommand();
            case LOCALE_COMMAND:
                return new LocaleCommand();
            default:
                //todo error page
                throw new IllegalArgumentException(command + " is unknown Command implementation!");
        }
    }
}
