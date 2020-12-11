package com.epam.movierating.command;

import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.MovieService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.entity.Movie;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class DeleteMovieCommand implements Command {

    private final MovieService movieService;

    public DeleteMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String idParameter = request.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        Optional<Movie> found = movieService.getById(id);
        String posterPath;
        if (found.isPresent()) {
            Movie movie = found.get();
            posterPath = movie.getPosterPath();
        } else {
            throw new ServiceException("No movie found to delete!");
        }

        movieService.deleteMovieById(id);

        if (posterPath != null) {
            ServletContext servletContext = request.getServletContext();
            String applicationPath = servletContext.getRealPath("");
            Path path = Paths.get(applicationPath, posterPath);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                throw new ServiceException(e);
            }

        }

        return CommandResult.redirect(Page.INDEX);
    }
}
