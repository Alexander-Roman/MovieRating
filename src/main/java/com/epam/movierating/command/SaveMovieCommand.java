package com.epam.movierating.command;

import com.epam.movierating.command.context.CommandResult;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.entity.Movie;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;

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
    private final MovieService movieService;

    public SaveMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        ServletContext servletContext = request.getServletContext();
        String applicationPath = servletContext.getRealPath("");

        String idParameter = request.getParameter(Parameter.ID);
        Long id = null;
        if (idParameter != null && !idParameter.isEmpty()) {
            id = Long.parseLong(idParameter);
        }
        String title = request.getParameter(Parameter.TITLE);
        String director = request.getParameter(Parameter.DIRECTOR);
        String yearParameter = request.getParameter(Parameter.RELEASE_YEAR);
        Integer releaseYear = null;
        if (yearParameter != null && !yearParameter.isEmpty()) {
            releaseYear = Integer.parseInt(yearParameter);
        }
        String synopsis = request.getParameter(Parameter.SYNOPSIS);
        String posterPath = request.getParameter(Parameter.POSTER_PATH);
        if (posterPath == null || posterPath.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            posterPath = POSTERS_DIRECTORY + uuid;
        }
        String ratingParameter = request.getParameter(Parameter.RATING);
        Double rating = null;
        if (ratingParameter != null && !ratingParameter.isEmpty()) {
            rating = Double.parseDouble(ratingParameter);
        }

        Movie movie = new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
        long confirmedId = movieService.save(movie);


        Part poster;
        try {
            poster = request.getPart(POSTER_PART);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }

        if (poster != null && poster.getSize() > 0) {
            Path path = Paths.get(applicationPath, posterPath);
            try (InputStream inputStream = poster.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }

        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + "/controller?command=movie&movie=" + confirmedId);
    }
}
