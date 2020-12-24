package com.epam.movierating.command;

import com.epam.movierating.constant.CommandName;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.entity.Movie;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class SaveMovieCommand implements Command {

    private static final String POSTER_PART = "poster";
    private static final String POSTERS_DIRECTORY = "/static/img/posters/";
    private static final String MOVIE_COMMAND_PATH = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.MOVIE;
    private final MovieService movieService;
    private final Validator<Part> posterValidator;

    public SaveMovieCommand(MovieService movieService, Validator<Part> posterValidator) {
        this.movieService = movieService;
        this.posterValidator = posterValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        Long id = null;
        if (idParameter != null && !idParameter.isEmpty()) {
            id = Long.parseLong(idParameter);
        }

        String title = request.getParameter(Parameter.TITLE);

        String director = request.getParameter(Parameter.DIRECTOR);
        if (director != null && director.isEmpty()) {
            director = null;
        }

        String yearParameter = request.getParameter(Parameter.RELEASE_YEAR);
        Integer releaseYear = null;
        if (yearParameter != null && !yearParameter.isEmpty()) {
            releaseYear = Integer.parseInt(yearParameter);
        }

        String synopsis = request.getParameter(Parameter.SYNOPSIS);
        if (synopsis != null && synopsis.isEmpty()) {
            synopsis = null;
        }

        String posterPath = request.getParameter(Parameter.POSTER_PATH);
        if (posterPath != null && posterPath.isEmpty()) {
            posterPath = null;
        }

        String ratingParameter = request.getParameter(Parameter.RATE);
        Double rating = null;
        if (ratingParameter != null && !ratingParameter.isEmpty()) {
            rating = Double.parseDouble(ratingParameter);
        }


        Part poster;
        try {
            poster = request.getPart(POSTER_PART);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }


        if (poster != null && poster.getSize() > 0) {
            if (!posterValidator.isValid(poster)) {
                throw new ServiceException("Invalid poster object: " + poster);
            }

            if (posterPath == null) {
                UUID uuid = UUID.randomUUID();
                posterPath = POSTERS_DIRECTORY + uuid;
            }

            ServletContext servletContext = request.getServletContext();
            String applicationPath = servletContext.getRealPath("");
            Path path = Paths.get(applicationPath, posterPath);

            try (InputStream inputStream = poster.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }

        Movie movie = new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
        long confirmedId = movieService.save(movie);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MOVIE_COMMAND_PATH + "&" + Parameter.ID + "=" + confirmedId);
    }
}