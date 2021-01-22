package com.epam.movierating.command.ajax;

import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The interface describes any action performed with AJAX.
 */
public interface AjaxCommand {

    /**
     * Performs the necessary actions using the parameters of HttpServletRequest object
     * and returns String for response body
     * @param request HttpServletRequest entity of the current request
     * @return response body String
     * @throws ServiceException in case of logical errors and also as a wrapper
     *                          for checked exceptions of lower application levels
     */
    Optional<String> execute(HttpServletRequest request) throws ServiceException;
}
