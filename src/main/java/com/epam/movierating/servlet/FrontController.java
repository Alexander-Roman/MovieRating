package com.epam.movierating.servlet;

import com.epam.movierating.command.Command;
import com.epam.movierating.command.CommandFactory;
import com.epam.movierating.connection.ConnectionPool;
import com.epam.movierating.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMAND_PARAM = "command";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        try {
            String commandRequest = request.getParameter(COMMAND_PARAM);
            Command command = CommandFactory.create(commandRequest);
            CommandResult commandResult = command.execute(request);
            String page = commandResult.getPage();

            if (commandResult.isRedirect()) {
                response.sendRedirect(page);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        super.destroy();
    }
}
