package com.epam.movierating.command;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGIN_PAGE_COMMAND = "loginPage";
    private static final String HOME_PAGE_COMMAND = "homePage";
    private static final String MOVIE_PAGE_COMMAND = "moviePage";

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
            default:
                //todo error page
                throw new IllegalArgumentException(command + " is unknown Command implementation!");
        }
    }
}
