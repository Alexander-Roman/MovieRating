package com.epam.movierating.model;

import com.epam.movierating.constant.CommandName;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    USER(
            Stream.of(
                    CommandName.USER_LIST
            ).collect(Collectors.toSet())
    ),
    EDITOR(
            Stream.of(
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.DELETE_COMMENT,
                    CommandName.USER_LIST
            ).collect(Collectors.toSet())
    ),
    ADMIN(
            Stream.of(
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.DELETE_MOVIE,
                    CommandName.DELETE_COMMENT,
                    CommandName.USER_LIST
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
