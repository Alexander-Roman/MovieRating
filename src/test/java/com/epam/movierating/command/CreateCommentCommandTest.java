package com.epam.movierating.command;

import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateCommentCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final String INVALID_ID_PARAM = "id";
    private static final String VALID_TEXT_PARAM = "text";

    private CommentService commentService;
    private CreateCommentCommand createCommentCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;

    @BeforeMethod
    public void setUp() {
        commentService = Mockito.mock(CommentService.class);
        createCommentCommand = new CreateCommentCommand(commentService);
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        servletContext = Mockito.mock(ServletContext.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() throws ServiceException {
        //given
        //when
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getParameter(Parameter.TEXT)).thenReturn(VALID_TEXT_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        CommandResult commandResult = createCommentCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldCreateComment() throws ServiceException {
        //given
        //when
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getParameter(Parameter.TEXT)).thenReturn(VALID_TEXT_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        createCommentCommand.execute(request);
        //then
        verify(commentService, times(1)).createNewComment(any());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        when(request.getParameter(Parameter.TEXT)).thenReturn(VALID_TEXT_PARAM);
        when(request.getServletContext()).thenReturn(servletContext);
        createCommentCommand.execute(request);
        //then
    }
}
