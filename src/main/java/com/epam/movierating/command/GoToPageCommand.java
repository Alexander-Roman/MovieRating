package com.epam.movierating.command;

import com.epam.movierating.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToPageCommand implements Command {

    private final String page;

    public GoToPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(page);
    }
}
