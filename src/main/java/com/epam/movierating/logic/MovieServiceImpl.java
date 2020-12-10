package com.epam.movierating.logic;

import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.entity.Movie;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    private final DaoConnectionManagerFactory factory;

    public MovieServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Movie> getById(String id) throws ServiceException {
        long movieId = Long.parseLong(id);
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.find(movieId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getPage(int page, int itemsPerPage) throws ServiceException {
        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.findBatch(itemsPerPage, firstItem);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            long numberOfItems = movieDao.getMoviesAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long save(Movie movie) throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.save(movie);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
