package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;
import com.epam.movierating.entity.CommentTo;

public interface DaoConnectionManager extends AutoCloseable {

    MovieDao createMovieDao();

    AccountDao createAccountDao();

    UserRatingToDao createUserRatingToDao();

    CommentToDao createCommentToDao();

    void beginTransaction() throws DaoException;

    void commitTransaction() throws DaoException;
}
