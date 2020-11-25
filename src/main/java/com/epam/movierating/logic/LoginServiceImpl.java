package com.epam.movierating.logic;

public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(String user, String password) {
        return user.equals(password);
    }
}
