package com.epam.movierating.logic;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.MySqlMovieDao;
import com.epam.movierating.entity.Movie;

import java.io.IOException;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Override
    public List<Movie> getAll() throws ServiceException {
        try (MovieDao dao = new MySqlMovieDao()) {
            return dao.getAll();
        } catch (DaoException | IOException e) {
            throw new ServiceException(e);
        }
    }
}
