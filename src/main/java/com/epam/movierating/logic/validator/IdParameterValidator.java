package com.epam.movierating.logic.validator;

public class IdParameterValidator implements ParameterValidator<Long> {

    @Override
    public Long validate(String parameter) {
        try {
            return Long.parseLong(parameter);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
