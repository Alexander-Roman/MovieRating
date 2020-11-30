package com.epam.movierating.logic;

import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.entity.Movie;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final DaoConnectionManagerFactory factory;

    public MovieServiceImpl() {
        factory = new DaoConnectionManagerFactory();
    }

    public MovieServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Movie> getAll() throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.findAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
