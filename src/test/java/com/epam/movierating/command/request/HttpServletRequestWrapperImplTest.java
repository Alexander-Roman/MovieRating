package com.epam.movierating.command.request;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    private static final String MAP_KEY = "key";

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

    @Test
    public void testGetParameterValuesShouldPerformAmpersandCharEscape() {
        //given
        String[] originalParameterValues = new String[]{AMPERSAND};
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        String[] actual = requestWrapper.getParameterValues(anyString());
        //then
        String[] expected = new String[]{AMPERSAND_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterValuesShouldPerformLessThanCharEscape() {
        //given
        String[] originalParameterValues = new String[]{LESS_THAN};
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        String[] actual = requestWrapper.getParameterValues(anyString());
        //then
        String[] expected = new String[]{LESS_THAN_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterValuesShouldPerformGreaterThanCharEscape() {
        //given
        String[] originalParameterValues = new String[]{GREATER_THAN};
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        String[] actual = requestWrapper.getParameterValues(anyString());
        //then
        String[] expected = new String[]{GREATER_THAN_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterValuesShouldTrimOriginalValues() {
        //given
        String[] originalParameterValues = new String[]{STRING_BLANK};
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        String[] actual = requestWrapper.getParameterValues(anyString());
        //then
        String[] expected = new String[]{STRING_EMPTY};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterValuesShouldReturnNullWhenParameterNotDefined() {
        //given
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(null);
        String[] actual = requestWrapper.getParameterValues(anyString());
        //then
        Assert.assertNull(actual);
    }

    @Test
    public void testGetParameterMapShouldPerformAmpersandCharEscape() {
        //given
        String[] originalParameterValues = new String[]{AMPERSAND};
        Map<String, String[]> originalParameterMap = new HashMap<>();
        originalParameterMap.put(MAP_KEY, originalParameterValues);
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        when(requestOriginal.getParameterMap()).thenReturn(originalParameterMap);
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        //then
        String[] actual = resultParameterMap.get(MAP_KEY);
        String[] expected = new String[]{AMPERSAND_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterMapShouldPerformLessThanCharEscape() {
        //given
        String[] originalParameterValues = new String[]{LESS_THAN};
        Map<String, String[]> originalParameterMap = new HashMap<>();
        originalParameterMap.put(MAP_KEY, originalParameterValues);
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        when(requestOriginal.getParameterMap()).thenReturn(originalParameterMap);
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        //then
        String[] actual = resultParameterMap.get(MAP_KEY);
        String[] expected = new String[]{LESS_THAN_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterMapShouldPerformGreaterThanCharEscape() {
        //given
        String[] originalParameterValues = new String[]{GREATER_THAN};
        Map<String, String[]> originalParameterMap = new HashMap<>();
        originalParameterMap.put(MAP_KEY, originalParameterValues);
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        when(requestOriginal.getParameterMap()).thenReturn(originalParameterMap);
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        //then
        String[] actual = resultParameterMap.get(MAP_KEY);
        String[] expected = new String[]{GREATER_THAN_ESCAPED};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterMapShouldTrimOriginalValues() {
        //given
        String[] originalParameterValues = new String[]{STRING_BLANK};
        Map<String, String[]> originalParameterMap = new HashMap<>();
        originalParameterMap.put(MAP_KEY, originalParameterValues);
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(originalParameterValues);
        when(requestOriginal.getParameterMap()).thenReturn(originalParameterMap);
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        //then
        String[] actual = resultParameterMap.get(MAP_KEY);
        String[] expected = new String[]{STRING_EMPTY};
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetParameterMapShouldNotIncludeParameterThatNotDefined() {
        //given
        Map<String, String[]> originalParameterMap = new HashMap<>();
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(null);
        when(requestOriginal.getParameterMap()).thenReturn(originalParameterMap);
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        //then
        String[] actual = resultParameterMap.get(MAP_KEY);
        Assert.assertNull(actual);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testGetParameterMapShouldReturnUnmodifiableMap() {
        //given
        //when
        when(requestOriginal.getParameterValues(anyString())).thenReturn(null);
        when(requestOriginal.getParameterMap()).thenReturn(Collections.emptyMap());
        Map<String, String[]> resultParameterMap = requestWrapper.getParameterMap();
        resultParameterMap.put(MAP_KEY, new String[]{});
        //then
    }
}
