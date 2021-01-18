package com.epam.movierating.logic;

import com.epam.movierating.dao.CommentDtoDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.manager.DaoConnectionManager;
import com.epam.movierating.dao.manager.DaoConnectionManagerFactory;
import com.epam.movierating.logic.validator.CommentDtoValidator;
import com.epam.movierating.logic.validator.Validator;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;
    private static final Account ACCOUNT = new Account(VALID_ID, "username", "password", Role.USER, false);
    private static final CommentDto COMMENT_DTO_EXISTING = new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), "text");
    private static final CommentDto COMMENT_DTO_CREATED = new CommentDto(null, VALID_ID, ACCOUNT, LocalDateTime.now(), "text");

    private DaoConnectionManagerFactory factory;
    private Validator<CommentDto> commentDtoValidator;
    private CommentServiceImpl commentService;
    private DaoConnectionManager daoConnectionManager;
    private CommentDtoDao commentDtoDao;

    @BeforeMethod
    public void setUp() throws DaoException {
        factory = Mockito.mock(DaoConnectionManagerFactory.class);
        commentDtoValidator = Mockito.mock(CommentDtoValidator.class);
        commentService = new CommentServiceImpl(factory, commentDtoValidator);
        daoConnectionManager = Mockito.mock(DaoConnectionManager.class);
        commentDtoDao = Mockito.mock(CommentDtoDao.class);

        when(factory.create()).thenReturn(daoConnectionManager);
        when(daoConnectionManager.createCommentDtoDao()).thenReturn(commentDtoDao);
        when(commentDtoValidator.isValid(any())).thenReturn(true);
        when(commentDtoDao.find(VALID_ID)).thenReturn(Optional.of(COMMENT_DTO_EXISTING));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetMovieCommentsShouldThrowExceptionWhenIdInvalid() throws ServiceException {
        //given
        //when
        commentService.getMovieComments(INVALID_ID);
        //then
    }

    @Test
    public void testGetMovieCommentsShouldReturnComments() throws ServiceException, DaoException {
        //given
        //when
        when(commentDtoDao.getByMovieId(VALID_ID)).thenReturn(Collections.singletonList(COMMENT_DTO_EXISTING));
        List<CommentDto> actual = commentService.getMovieComments(VALID_ID);
        //then
        List<CommentDto> expected = Collections.singletonList(COMMENT_DTO_EXISTING);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testDeleteCommentByIdShouldThrowExceptionWhenCommentNotExist() throws ServiceException, DaoException {
        //given
        //when
        when(commentDtoDao.find(anyLong())).thenReturn(Optional.empty());
        commentService.deleteCommentById(VALID_ID);
        //then
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteCommentByIdShouldThrowExceptionWhenIdInvalid() throws ServiceException {
        //given
        //when
        commentService.deleteCommentById(INVALID_ID);
        //then
    }

    @Test
    public void testDeleteCommentByIdShouldDeleteComment() throws ServiceException, DaoException {
        //given
        //when
        commentService.deleteCommentById(VALID_ID);
        //then
        verify(commentDtoDao, times(1)).delete(VALID_ID);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testCreateNewCommentShouldThrowExceptionWhenCommentDtoInvalid() throws ServiceException {
        //given
        //when
        when(commentDtoValidator.isValid(any())).thenReturn(false);
        commentService.createNewComment(COMMENT_DTO_CREATED);
        //then
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testCreateNewCommentShouldThrowExceptionWhenIdIsPresent() throws ServiceException {
        //given
        //when
        commentService.createNewComment(COMMENT_DTO_EXISTING);
        //then
    }

    @Test
    public void testCreateNewCommentShouldSaveCommentDto() throws ServiceException, DaoException {
        //given
        //when
        commentService.createNewComment(COMMENT_DTO_CREATED);
        //then
        verify(commentDtoDao, times(1)).save(COMMENT_DTO_CREATED);
    }
}
