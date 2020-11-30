package com.epam.movierating.command;

import javax.servlet.http.HttpServletRequest;

public class GoToPageCommand implements Command {

    private final String page;

    public GoToPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.forward(page);
    }
}
