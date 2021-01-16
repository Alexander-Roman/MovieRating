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

        //HashMap<String, String> parameters = extractParameters(request);
        //Movie movie = extractFromParameters(parameters);

        Movie movie = extractFromRequest(request);

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

            String posterPath = movie.getPosterPath();
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

            movie = new Movie.Builder(movie)
                    .setPosterPath(posterPath)
                    .build();
        }

        long confirmedId = movieService.save(movie);

        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MOVIE_COMMAND_PATH + "&" + Parameter.ID + "=" + confirmedId);
    }

    private Movie extractFromRequest(HttpServletRequest request) {
        String idParam = request.getParameter(Parameter.ID);
        Long id = idParam.isEmpty()
                ? null
                : Long.parseLong(idParam);

        String titleParam = request.getParameter(Parameter.TITLE);
        String title = titleParam.isEmpty()
                ? null
                : normalizeIncomingStringData(titleParam);

        String directorParam = request.getParameter(Parameter.DIRECTOR);
        String director = directorParam.isEmpty()
                ? null
                : normalizeIncomingStringData(directorParam);

        String yearParam = request.getParameter(Parameter.RELEASE_YEAR);
        Integer releaseYear = yearParam.isEmpty()
                ? null
                : Integer.parseInt(yearParam);

        String synopsisParam = request.getParameter(Parameter.SYNOPSIS);
        String synopsis = synopsisParam.isEmpty()
                ? null
                : normalizeIncomingStringData(synopsisParam);

        String posterPathParam = request.getParameter(Parameter.POSTER_PATH);
        String posterPath = posterPathParam.isEmpty()
                ? null
                : normalizeIncomingStringData(posterPathParam);

        String ratingParam = request.getParameter(Parameter.RATING);
        Double rating = ratingParam.isEmpty()
                ? null
                : Double.parseDouble(ratingParam);

        return new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
    }

    private String normalizeIncomingStringData(String parameter) {
        return parameter
                .trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    /*
    private HashMap<String, String> extractParameters(HttpServletRequest request) {
        HashMap<String, String> parameters = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            String value = request.getParameter(key);
            value = value.trim();
            if (!value.isEmpty()) {
                value = value
                        .replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;");
                parameters.put(key, value);
            }
        }
        return parameters;
    }
     */

    /*
    private Movie extractFromParameters(HashMap<String, String> parameters) {
        String idValue = parameters.get(Parameter.ID);
        Long id = idValue != null
                ? Long.parseLong(idValue)
                : null;
        String title = parameters.get(Parameter.TITLE);
        String director = parameters.get(Parameter.DIRECTOR);
        String yearValue = parameters.get(Parameter.RELEASE_YEAR);
        Integer releaseYear = yearValue != null
                ? Integer.parseInt(yearValue)
                : null;
        String synopsis = parameters.get(Parameter.SYNOPSIS);
        String posterPath = parameters.get(Parameter.POSTER_PATH);
        String ratingValue = parameters.get(Parameter.RATE);
        Double rating = ratingValue != null
                ? Double.parseDouble(ratingValue)
                : null;
        return new Movie(id, title, director, releaseYear, synopsis, posterPath, rating);
    }
     */
}