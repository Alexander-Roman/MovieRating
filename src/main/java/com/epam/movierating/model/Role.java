package com.epam.movierating.model;

import com.epam.movierating.constant.CommandName;

import java.util.Arrays;
import java.util.List;

public enum Role {
    GUEST(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGIN,
                    CommandName.LOGIN_PAGE,
                    CommandName.MOVIE
            )
    ),
    USER(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.MOVIE,
                    CommandName.CREATE_COMMENT,
                    CommandName.RATE_MOVIE
            )
    ),
    EDITOR(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.BLOCK_USER,
                    CommandName.UNBLOCK_USER,
                    CommandName.MOVIE,
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.CREATE_COMMENT,
                    CommandName.DELETE_COMMENT,
                    CommandName.RATE_MOVIE
            )
    ),
    ADMIN(
            Arrays.asList(
                    CommandName.HOME,
                    CommandName.LOCALIZATION,
                    CommandName.LOGOUT,
                    CommandName.USER_LIST,
                    CommandName.BLOCK_USER,
                    CommandName.UNBLOCK_USER,
                    CommandName.BLOCK_EDITOR,
                    CommandName.UNBLOCK_EDITOR,
                    CommandName.PROMOTE_USER,
                    CommandName.DEMOTE_EDITOR,
                    CommandName.MOVIE,
                    CommandName.CREATE_MOVIE,
                    CommandName.EDIT_MOVIE,
                    CommandName.SAVE_MOVIE,
                    CommandName.DELETE_MOVIE,
                    CommandName.CREATE_COMMENT,
                    CommandName.DELETE_COMMENT,
                    CommandName.RATE_MOVIE
            )
    );

    private final List<String> permissions;

    Role(List<String> permissions) {
        this.permissions = permissions;
    }

    public boolean hasAccess(String name) {
        return permissions.contains(name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "permissions=" + permissions +
                '}';
    }
}
