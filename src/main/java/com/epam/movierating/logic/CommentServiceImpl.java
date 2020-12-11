package com.epam.movierating.logic;

import com.epam.movierating.dao.CommentDtoDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Movie;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final DaoConnectionManagerFactory factory;

    public CommentServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<CommentDto> getMovieComments(Movie movie) throws ServiceException {
        Long movieId = movie.getId();
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            return commentDtoDao.getByMovieId(movieId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentById(long id) throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            commentDtoDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
