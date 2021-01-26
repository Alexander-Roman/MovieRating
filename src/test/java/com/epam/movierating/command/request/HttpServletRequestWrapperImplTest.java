package com.epam.movierating.command.request;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class HttpServletRequestWrapperImplTest {

    private static final String AMPERSAND = "&";
    private static final String AMPERSAND_ESCAPED = "&amp;";
    private static final String LESS_THAN = "<";
    private static final String LESS_THAN_ESCAPED = "&lt;";
    private static final String GREATER_THAN = ">";
    private static final String GREATER_THAN_ESCAPED = "&gt;";
    private static final String STRING_BLANK = " \n ";
    private static final String STRING_EMPTY = "";

    private HttpServletRequest requestOriginal;
    private HttpServletRequestWrapperImpl requestWrapper;

    @BeforeMethod
    public void setUp() {
        requestOriginal = Mockito.mock(HttpServletRequest.class);
        requestWrapper = new HttpServletRequestWrapperImpl(requestOriginal);
    }

    @Test
    public void testGetParameterShouldPerformAmpersandCharEscape() {
        //given
        //when
        when(requestOriginal.getParameter(anyString())).thenReturn(AMPERSAND);
        String actual = requestWrapper.getParameter(anyString());
        //then
        Assert.assertEquals(actual, AMPERSAND_ESCAPED);
    }

    @Test
    public void testGetParameterShouldPerformLessThanCharEscape() {
        //given
        //when
        when(requestOriginal.getParameter(anyString())).thenReturn(LESS_THAN);
        String actual = requestWrapper.getParameter(anyString());
        //then
        Assert.assertEquals(actual, LESS_THAN_ESCAPED);
    }

    @Test
    public void testGetParameterShouldPerformGreaterThanCharEscape() {
        //given
        //when
        when(requestOriginal.getParameter(anyString())).thenReturn(GREATER_THAN);
        String actual = requestWrapper.getParameter(anyString());
        //then
        Assert.assertEquals(actual, GREATER_THAN_ESCAPED);
    }

    @Test
    public void testGetParameterShouldTrimOriginalValue() {
        //given
        //when
        when(requestOriginal.getParameter(anyString())).thenReturn(STRING_BLANK);
        String actual = requestWrapper.getParameter(anyString());
        //then
        Assert.assertEquals(actual, STRING_EMPTY);
    }

    @Test
    public void testGetParameterShouldReturnNullWhenParameterNotDefined() {
        //given
        //when
        when(requestOriginal.getParameter(anyString())).thenReturn(null);
        String actual = requestWrapper.getParameter(anyString());
        //then
        Assert.assertNull(actual);
    }
}
