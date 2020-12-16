package com.epam.movierating.logic.validator;

public interface Validator<T> {

    boolean isValid(T object);
}
