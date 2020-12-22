package com.epam.movierating.logic;

import com.epam.movierating.model.entity.Movie;

import java.util.List;

public interface MovieService {

    int getNumberOfPages(int itemsPerPage) throws ServiceException;

    List<Movie> getPage(int page, int itemsPerPage) throws ServiceException;

    Movie getById(long id) throws ServiceException;

    long save(Movie movie) throws ServiceException;

    void deleteMovieById(long id) throws ServiceException;

}
