package com.epam.movierating.logic;

import com.epam.movierating.view.localization.LocalizationManager;

public class LocalizationServiceImpl implements LocalizationService {

    @Override
    public LocalizationManager getLocalizationManager(String parameter) {
        if (parameter == null) {
            return LocalizationManager.EN;
        }
        switch (parameter) {
            case "EN":
            case "BE":
            case "RU":
                return LocalizationManager.valueOf(parameter);
            default:
                return LocalizationManager.EN;
        }
    }
}
