package com.epam.movierating.command.context;

import java.util.Objects;

public final class CommandResult {

    private final String page;
    private final Boolean redirect;

    private CommandResult(String page, Boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public Boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(redirect, that.redirect);
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (redirect != null ? redirect.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "page='" + page + '\'' +
                ", redirect=" + redirect +
                '}';
    }
}
