package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;

public interface DaoConnectionManager extends AutoCloseable {

    MovieDao createMovieDao();

    AccountDao createAccountDao();

    UserRatingDtoDao createUserRatingDtoDao();

    CommentDtoDao createCommentDtoDao();

    void beginTransaction() throws DaoException;

    void commitTransaction() throws DaoException;
}
