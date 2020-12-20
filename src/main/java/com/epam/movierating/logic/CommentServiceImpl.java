package com.epam.movierating.logic;

import com.epam.movierating.dao.CommentDtoDao;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.CommentDto;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final long MIN_ID_VALUE = 1L;
    private final DaoConnectionManagerFactory factory;
    private final Validator<CommentDto> commentDtoValidator;

    public CommentServiceImpl(DaoConnectionManagerFactory factory, Validator<CommentDto> commentDtoValidator) {
        this.factory = factory;
        this.commentDtoValidator = commentDtoValidator;
    }

    @Override
    public List<CommentDto> getMovieComments(long movieId) throws ServiceException {
        if (movieId < MIN_ID_VALUE) {
            throw new ServiceException("Invalid movie id value: " + movieId);
        }
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            return commentDtoDao.getByMovieId(movieId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentById(long id) throws ServiceException {
        if (id < MIN_ID_VALUE) {
            throw new ServiceException("Invalid comment id value: " + id);
        }
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            commentDtoDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long createNewComment(CommentDto commentDto) throws ServiceException {
        if (!commentDtoValidator.isValid(commentDto)) {
            throw new ServiceException("Invalid CommentDto object!");
        }
        Long id = commentDto.getId();
        if (id != null) {
            throw new ServiceException("New comment should not contain id!");
        }
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            return commentDtoDao.save(commentDto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
