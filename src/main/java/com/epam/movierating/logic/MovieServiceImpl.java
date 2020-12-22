package com.epam.movierating.logic;

import com.epam.movierating.dao.MovieDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.entity.Movie;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ITEM_PER_PAGE_VALUE = 1;
    private static final int MIN_PAGE_VALUE = 1;
    private final DaoConnectionManagerFactory factory;
    private final Validator<Movie> movieValidator;

    public MovieServiceImpl(DaoConnectionManagerFactory factory, Validator<Movie> movieValidator) {
        this.factory = factory;
        this.movieValidator = movieValidator;
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws ServiceException {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_PER_PAGE_VALUE, "Invalid itemsPerPage value: " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            long numberOfItems = movieDao.getMoviesAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getPage(int page, int itemsPerPage) throws ServiceException {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_PER_PAGE_VALUE, "Invalid itemsPerPage value: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.findBatch(itemsPerPage, firstItem);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Movie getById(long id) throws ServiceException {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            Optional<Movie> found = movieDao.find(id);
            return found.orElseThrow(() -> new NotFoundException("No movie found by id: " + id));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long save(Movie movie) throws ServiceException {
        if (!movieValidator.isValid(movie)) {
            throw new ServiceException("Invalid Movie object: " + movie);
        }

        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            return movieDao.save(movie);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteMovieById(long id) throws ServiceException {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            MovieDao movieDao = manager.createMovieDao();
            movieDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
