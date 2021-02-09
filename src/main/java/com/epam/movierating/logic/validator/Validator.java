package com.epam.movierating.logic.validator;

/**
 * The interface describes entity validation classes
 * @param <T> type of objects for validation
 */
public interface Validator<T> {

    /**
     * Checks the validity of an object
     * @param object entity to be validated
     * @return true if valid
     */
    boolean isValid(T object);
}
