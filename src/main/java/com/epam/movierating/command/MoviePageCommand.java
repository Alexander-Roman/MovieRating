package com.epam.movierating.command;

import com.epam.movierating.constant.Page;

import javax.servlet.http.HttpServletRequest;

public class MoviePageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.forward(Page.MOVIE);
    }
}
