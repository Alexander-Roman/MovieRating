package com.epam.movierating.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class PageForwardCommand implements Command {

    private final String page;

    public PageForwardCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.forward(page);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageForwardCommand that = (PageForwardCommand) o;
        return Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return page != null ? page.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "page='" + page + '\'' +
                '}';
    }
}
