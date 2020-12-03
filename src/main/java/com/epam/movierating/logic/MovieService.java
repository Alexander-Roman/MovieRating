package com.epam.movierating.logic;

import com.epam.movierating.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getPage(int page, int itemsPerPage) throws ServiceException;

    int getNumberOfPages(int itemsPerPage) throws ServiceException;
}
