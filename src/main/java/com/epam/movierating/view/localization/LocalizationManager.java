package com.epam.movierating.view.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocalizationManager {

    EN(ResourceBundle.getBundle("property.localization", Locale.ENGLISH)),
    RU(ResourceBundle.getBundle("property.localization", new Locale("ru", "BY")));

    private final ResourceBundle bundle;

    LocalizationManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
