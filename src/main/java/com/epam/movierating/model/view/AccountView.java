package com.epam.movierating.model.view;

import com.epam.movierating.model.Identifiable;
import com.epam.movierating.model.entity.Account;

import java.util.Objects;

public class AccountView implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String userName;

    public AccountView(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public static AccountView from(Account account) {
        Long id = account.getId();
        String userName = account.getUserName();
        return new AccountView(id, userName);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountView that = (AccountView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
