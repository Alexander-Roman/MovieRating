package com.epam.movierating.servlet;

import com.epam.movierating.command.ajax.AjaxCommand;
import com.epam.movierating.command.ajax.AjaxCommandContext;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(urlPatterns="/ajax")
public class AjaxServlet extends HttpServlet {

    private static final int NOT_FOUND_ERROR_CODE = 404;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static final Logger LOGGER = LogManager.getLogger();

    private final AjaxCommandContext ajaxCommandContext = new AjaxCommandContext();

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
            AjaxCommand ajaxCommand = ajaxCommandContext.getByCommandName(commandRequest);
            Optional<String> result = ajaxCommand.execute(request);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (result.isPresent()) {
                PrintWriter printWriter = response.getWriter();
                String json = result.get();
                printWriter.write(json);
            }
        } catch (NotFoundException e) {
            LOGGER.debug(e);
            response.sendError(NOT_FOUND_ERROR_CODE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            response.sendError(INTERNAL_SERVER_ERROR_CODE);
        }
    }
}
