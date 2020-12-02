package com.epam.movierating.logic.validator;

public class PageValidatorImpl implements PageValidator {

    private static final int DEFAULT_PAGE_NUMBER = 1;

    @Override
    public int validate(String page) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            return DEFAULT_PAGE_NUMBER;
        }
        if (pageNumber < 1) {
            return DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
