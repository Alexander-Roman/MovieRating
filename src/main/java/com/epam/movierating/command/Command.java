package com.epam.movierating.command;

import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface describes any action performed on the server side of the application.
 */
public interface Command {

    /**
     * Performs the necessary actions using the parameters of HttpServletRequest object
     * and returns CommandResult object with further page jump instructions
     *
     * @param request HttpServletRequest entity of the current request
     * @return CommandResult object with page path and redirection type
     * @throws ServiceException in case of logical errors and also as a wrapper
     *                          for checked exceptions of lower application levels
     */
    CommandResult execute(HttpServletRequest request) throws ServiceException;

}
