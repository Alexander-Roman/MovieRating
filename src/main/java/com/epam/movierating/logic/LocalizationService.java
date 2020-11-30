package com.epam.movierating.logic;

import com.epam.movierating.view.localization.LocalizationManager;

public interface LocalizationService {

    LocalizationManager getLocalizationManager(String parameter);
}
