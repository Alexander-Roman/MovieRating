package com.epam.movierating.logic.validator;

import com.epam.movierating.view.localization.Localization;

public class LocalizationParameterValidator implements ParameterValidator<Localization> {

    @Override
    public Localization validate(String parameter) {
        if (parameter == null) {
            return Localization.EN;
        }
        switch (parameter) {
            case "EN":
            case "BE":
            case "RU":
                return Localization.valueOf(parameter);
            default:
                return Localization.EN;
        }
    }
}
