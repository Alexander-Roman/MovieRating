package com.epam.movierating.logic.validator;

public interface ParameterValidator<T> {

    T validate(String parameter);
}
