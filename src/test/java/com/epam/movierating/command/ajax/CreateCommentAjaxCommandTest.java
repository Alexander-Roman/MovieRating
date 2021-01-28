package com.epam.movierating.command.ajax;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.CommentService;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateCommentAjaxCommandTest {

    private static final String VALID_ID_PARAM = "1";
    private static final String INVALID_ID_PARAM = "id";
    private static final String VALID_TEXT_PARAM = "text";
    private static final Account ACCOUNT = new Account(1L, "userName", Role.USER, false);

    private CommentService commentService;
    private CreateCommentAjaxCommand createCommentCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;

    @BeforeMethod
    public void setUp() throws ServiceException {
        commentService = Mockito.mock(CommentService.class);
        createCommentCommand = new CreateCommentAjaxCommand(commentService);
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Attribute.ACCOUNT)).thenReturn(ACCOUNT);
        when(request.getParameter(Parameter.ID)).thenReturn(VALID_ID_PARAM);
        when(request.getParameter(Parameter.TEXT)).thenReturn(VALID_TEXT_PARAM);
        when(commentService.getMovieComments(anyLong())).thenReturn(Collections.emptyList());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testExecuteShouldThrowExceptionWhenIdParameterInvalid() throws ServiceException {
        //given
        //when
        when(request.getParameter(Parameter.ID)).thenReturn(INVALID_ID_PARAM);
        createCommentCommand.execute(request);
        //then
    }

    @Test
    public void testExecuteShouldCreateComment() throws ServiceException {
        //given
        //when
        createCommentCommand.execute(request);
        //then
        verify(commentService, times(1)).createNewComment(any());
    }

    @Test
    public void testExecuteShouldReturnCommentViewListJson() throws ServiceException {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 28, 16, 47);
        CommentDto commentDto = new CommentDto(1L, 1L, ACCOUNT, localDateTime, "text");
        //when
        when(commentService.getMovieComments(anyLong())).thenReturn(Collections.singletonList(commentDto));
        Optional<String> actual = createCommentCommand.execute(request);
        //then
        Optional<String> expected = Optional.of("[{\"id\":1,\"author\":{\"id\":1,\"userName\":\"userName\"},\"dateTime\":\"2021-01-28 16-47\",\"text\":\"text\"}]");
        Assert.assertEquals(actual, expected);
    }
}