package com.epam.movierating.command;

import com.epam.movierating.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest request) throws CommandException;
}
