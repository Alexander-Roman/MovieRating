package com.epam.movierating.servlet;

import com.epam.movierating.command.Command;
import com.epam.movierating.command.CommandFactory;
import com.epam.movierating.command.CommandResult;
import com.epam.movierating.connection.ConnectionPool;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.PageNotFoundException;
import com.epam.movierating.logic.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 4, //4MB
        maxRequestSize = 1024 * 1024 * 8) //8MB
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String commandRequest = request.getParameter(Parameter.COMMAND);
            Command command = CommandFactory.create(commandRequest);
            CommandResult commandResult = command.execute(request);
            String page = commandResult.getPage();

            if (commandResult.isRedirect()) {
                response.sendRedirect(page);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
            }
        } catch (PageNotFoundException e) {
            response.sendError(404);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            response.sendError(500);
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
