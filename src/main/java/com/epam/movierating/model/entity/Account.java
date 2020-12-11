package com.epam.movierating.model.entity;

import com.epam.movierating.model.Identifiable;
import com.epam.movierating.model.Role;

import java.util.Objects;

public final class Account implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String userName;
    private final String password;
    private final Role role;
    private final Boolean blocked;

    public Account(Long id, String userName, String password, Role role, Boolean blocked) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(userName, account.userName) &&
                Objects.equals(password, account.password) &&
                role == account.role &&
                Objects.equals(blocked, account.blocked);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (blocked != null ? blocked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
