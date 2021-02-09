package com.epam.movierating.logic;

import java.util.List;

/**
 * Describes the class of objects are paginated
 * @param <T> type of paginated entities
 */
public interface PaginationService<T> {

    /**
     * Calculates and returns the total number of available pages
     * @param itemsPerPage specified number of items for one page
     * @return number of pages available
     * @throws ServiceException in case of errors
     */
    int getNumberOfPages(int itemsPerPage) throws ServiceException;

    /**
     * Returns a list of entities for a given page
     * @param page requested page number
     * @param itemsPerPage specified number of items for one page
     * @return List of entities for the requested page
     * @throws ServiceException in case of errors
     */
    List<T> getPage(int page, int itemsPerPage) throws ServiceException;
}
