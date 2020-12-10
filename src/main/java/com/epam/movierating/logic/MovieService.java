package com.epam.movierating.logic;

import com.epam.movierating.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getPage(int page, int itemsPerPage) throws ServiceException;

    int getNumberOfPages(int itemsPerPage) throws ServiceException;

    Optional<Movie> getById(String id) throws ServiceException;

    long save(Movie movie) throws ServiceException;
}
