package com.epam.movierating.command;

import com.epam.movierating.command.context.CommandResult;
import com.epam.movierating.constant.Page;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CreateMovieCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.forward(Page.MOVIE_EDITOR);
    }
}
