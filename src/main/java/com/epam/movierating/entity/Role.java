package com.epam.movierating.entity;

import java.util.EnumSet;

public enum Role {
    USER(
            EnumSet.of(
                    Access.CREATE_COMMENT,
                    Access.USERS_OVERVIEW
            )
    ),
    EDITOR(
            EnumSet.of(
                    Access.CREATE_MOVIE,
                    Access.UPDATE_MOVIE,
                    Access.DELETE_MOVIE,
                    Access.BLOCK_USER,
                    Access.CREATE_COMMENT,
                    Access.DELETE_COMMENT,
                    Access.USERS_OVERVIEW
            )
    ),
    ADMIN(
            EnumSet.allOf(
                    Access.class
            )
    );

    private final EnumSet<Access> permissions;

    Role(EnumSet<Access> permissions) {
        this.permissions = permissions;
    }

    public boolean hasAccess(Access access) {
        return permissions.contains(access);
    }
}
