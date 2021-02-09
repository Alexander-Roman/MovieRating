package com.epam.movierating.dao.manager;

import com.epam.movierating.dao.*;

/**
 * Describes a class for managing multiple DAO instances in the context of a single database connection.
 * This is necessary to enable transactions. As a result, such a class combines the functions of
 * transaction management and DAO factory. Also separates some of the DAO layer dependencies and
 * possible SQL exceptions from the logic layers.
 */
public interface DaoConnectionManager extends AutoCloseable {

    /**
     * Provides MovieDao instance
     * @return MovieDao instance
     */
    MovieDao createMovieDao();

    /**
     * Provides AccountDao instance
     * @return AccountDao instance
     */
    AccountDao createAccountDao();

    /**
     * Provides UserRatingDtoDao instance
     * @return UserRatingDtoDao instance
     */
    UserRatingDtoDao createUserRatingDtoDao();

    /**
     * Provides CommentDtoDao instance
     * @return CommentDtoDao instance
     */
    CommentDtoDao createCommentDtoDao();

    /**
     * The method prepares the database connection for adding queries in a single transaction
     * @throws DaoException in case of errors
     */
    void beginTransaction() throws DaoException;

    /**
     * Executes all previously added within a transaction queries
     * @throws DaoException in case of errors
     */
    void commitTransaction() throws DaoException;

    /**
     * Method overriding restricts the type hierarchy of possible exceptions from AutoCloseable
     * @throws DaoException in case of errors
     */
    @Override
    void close() throws DaoException;
}
