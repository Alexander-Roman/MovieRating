package com.epam.movierating.logic;

import com.epam.movierating.dao.CommentDtoDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.dto.CommentDto;
import com.google.common.base.Preconditions;

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
        Preconditions.checkArgument(movieId >= MIN_ID_VALUE, "Invalid movieId value: " + movieId);

        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            return commentDtoDao.getByMovieId(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCommentById(long id) throws ServiceException {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            commentDtoDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long createNewComment(CommentDto commentDto) throws ServiceException {
        if (!commentDtoValidator.isValid(commentDto)) {
            throw new ServiceException("Invalid CommentDto object: " + commentDto);
        }

        Long id = commentDto.getId();
        if (id != null) {
            throw new ServiceException("New comment should not contain id!");
        }
        try (DaoConnectionManager manager = factory.create()) {
            CommentDtoDao commentDtoDao = manager.createCommentDtoDao();
            return commentDtoDao.save(commentDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
