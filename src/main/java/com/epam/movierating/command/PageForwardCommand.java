package com.epam.movierating.command;

import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class PageForwardCommand implements Command {

    private final String page;

    public PageForwardCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.forward(page);
    }
}
