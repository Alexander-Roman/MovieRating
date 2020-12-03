package com.epam.movierating.command;

import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String LOGIN = "login";
    String LOGIN_PAGE = "loginPage";
    String HOME = "home";
    String MOVIE = "movie";
    String LANGUAGE = "lang";

    CommandResult execute(HttpServletRequest request) throws ServiceException;
}
