package com.epam.movierating.model;

import com.epam.movierating.constant.CommandName;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    GUEST(
            Stream.of(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGIN,
                    CommandName.LOGIN_PAGE,
                    CommandName.MOVIE
            ).collect(Collectors.toSet())
    ),
    USER(
            Stream.of(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.MOVIE,
                    CommandName.RATE_MOVIE
            ).collect(Collectors.toSet())
    ),
    EDITOR(
            Stream.of(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.MOVIE,
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.DELETE_COMMENT,
                    CommandName.RATE_MOVIE
            ).collect(Collectors.toSet())
    ),
    ADMIN(
            Stream.of(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.MOVIE,
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.DELETE_MOVIE,
                    CommandName.DELETE_COMMENT,
                    CommandName.RATE_MOVIE
            ).collect(Collectors.toSet())
    );

    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public boolean hasAccess(String name) {
        return permissions.contains(name);
    }
}
