package com.epam.movierating.command;

import com.epam.movierating.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
