package com.epam.movierating.logic.validator;

public class PageParameterValidator implements ParameterValidator<Integer> {

    private static final int DEFAULT_PAGE_NUMBER = 1;

    @Override
    public Integer validate(String parameter) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            return DEFAULT_PAGE_NUMBER;
        }
        if (pageNumber < 1) {
            return DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
