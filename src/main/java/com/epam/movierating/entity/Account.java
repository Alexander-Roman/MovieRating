package com.epam.movierating.entity;

import java.util.Objects;

public final class Account {

    private final long accountId;
    private final String userName;
    private final String password;
    private final Role role;

    public Account(long accountId, String userName, String password, Role role) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public long getAccountId() {
        return accountId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(userName, account.userName) &&
                Objects.equals(password, account.password) &&
                role == account.role;
    }

    @Override
    public int hashCode() {
        int result = (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
