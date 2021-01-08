package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.logic.ServiceException;
import com.epam.movierating.model.Localization;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LocalizationCommandTest {

    private static final String VALID_EN_LANG_PARAM = "EN";
    private static final String INVALID_LANG_PARAM = "en";
    private static final String HEADER = "header";

    private final LocalizationCommand localizationCommand = new LocalizationCommand();
    private HttpServletRequest request;
    private HttpSession session;

    @BeforeMethod
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectCommandResult() {
        //given
        //when
        when(request.getParameter(Parameter.LANGUAGE)).thenReturn(VALID_EN_LANG_PARAM);
        when(request.getSession()).thenReturn(session);
        when(request.getHeader(anyString())).thenReturn(HEADER);
        CommandResult commandResult = localizationCommand.execute(request);
        //then
        boolean actual = commandResult.isRedirect();
        Assert.assertTrue(actual);
    }

    @Test
    public void testExecuteShouldSetSessionLocalizationAttribute() {
        //given
        //when
        when(request.getParameter(Parameter.LANGUAGE)).thenReturn(VALID_EN_LANG_PARAM);
        when(request.getSession()).thenReturn(session);
        when(request.getHeader(anyString())).thenReturn(HEADER);
        localizationCommand.execute(request);
        //then
        verify(session, times(1)).setAttribute(Attribute.LOCALIZATION, Localization.EN);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExecuteShouldThrowExceptionWhenLanguageParameterInvalid() {
        //given
        //when
        when(request.getParameter(Parameter.LANGUAGE)).thenReturn(INVALID_LANG_PARAM);
        when(request.getSession()).thenReturn(session);
        when(request.getHeader(anyString())).thenReturn(HEADER);
        localizationCommand.execute(request);
        //then
    }
}
