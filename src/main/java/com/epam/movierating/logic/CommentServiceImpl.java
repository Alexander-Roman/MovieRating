package com.epam.movierating.logic;

import com.epam.movierating.dao.CommentToDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.entity.CommentTo;
import com.epam.movierating.entity.Movie;

import java.util.List;

public class CommentServiceImpl extends AbstractService implements CommentService {

    public CommentServiceImpl(DaoConnectionManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<CommentTo> getMovieComments(Movie movie) throws ServiceException {
        Long movieId = movie.getId();
        try (DaoConnectionManager manager = createDaoConnectionManager()) {
            CommentToDao commentToDao = manager.createCommentToDao();
            return commentToDao.getByMovieId(movieId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
