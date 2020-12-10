package com.epam.movierating.command;

import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest request) throws ServiceException;

}
