package com.epam.movierating.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "WEB-INF/view/home.jsp";
    }
}
