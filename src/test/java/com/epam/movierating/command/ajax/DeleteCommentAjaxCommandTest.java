package com.epam.movierating.command.ajax;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DeleteCommentAjaxCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final long VALID_ID = 1L;
    private static final String INVALID_ID_PARAM = "id";

    private CommentService commentService;
    private DeleteCommentAjaxCommand deleteCommentCommand;
    private HttpServletRequest request;

    @BeforeMethod
    public void setUp() {
        commentService = Mockito.mock(CommentService.class);
        deleteCommentCommand = new DeleteCommentAjaxCommand(commentService);
        request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void testExecuteShouldReturnEmptyResult() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        Optional<String> actual = deleteCommentCommand.execute(request);
        //then
        Optional<String> expected = Optional.empty();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldDeleteComment() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        deleteCommentCommand.execute(request);
        //then
        verify(commentService, times(1)).deleteCommentById(VALID_ID);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        deleteCommentCommand.execute(request);
        //then
    }
}