package com.epam.movierating.logic.validator;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.Part;

import static org.mockito.Mockito.when;

public class PosterValidatorTest {

    private static final long VALID_POSTER_SIZE = 1024 * 512; // 0.5 MB
    private static final long INVALID_POSTER_SIZE = 1024 * 1024 * 2; // 2 MB
    private static final String VALID_CONTENT_TYPE_JPEG = "image/jpeg";
    private static final String VALID_CONTENT_TYPE_PNG = "image/png";
    private static final String INVALID_CONTENT_TYPE_TIFF = "image/tiff";

    private final PosterValidator posterValidator = new PosterValidator();

    private Part posterPart;

    @BeforeMethod
    public void setUp() {
        posterPart = Mockito.mock(Part.class);
    }

    @Test
    public void testIsValidShouldReturnTrueWhenPartIsValid() {
        //given
        //when
        positiveScenario();
        boolean actual = posterValidator.isValid(posterPart);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShouldReturnTrueWhenPngPartIsValid() {
        //given
        positiveScenario();
        //when
        when(posterPart.getContentType()).thenReturn(VALID_CONTENT_TYPE_PNG);
        boolean actual = posterValidator.isValid(posterPart);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPartIsNull() {
        //given
        positiveScenario();
        //when
        boolean actual = posterValidator.isValid(null);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPartZeroSize() {
        //given
        positiveScenario();
        //when
        when(posterPart.getSize()).thenReturn(0L);
        boolean actual = posterValidator.isValid(posterPart);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPartSizeInvalid() {
        //given
        positiveScenario();
        //when
        when(posterPart.getSize()).thenReturn(INVALID_POSTER_SIZE);
        boolean actual = posterValidator.isValid(posterPart);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenPartContentTypeInvalid() {
        //given
        positiveScenario();
        //when
        when(posterPart.getContentType()).thenReturn(INVALID_CONTENT_TYPE_TIFF);
        boolean actual = posterValidator.isValid(posterPart);
        //then
        Assert.assertFalse(actual);
    }
    
    private void positiveScenario() {
        when(posterPart.getSize()).thenReturn(VALID_POSTER_SIZE);
        when(posterPart.getContentType()).thenReturn(VALID_CONTENT_TYPE_JPEG);
    }
}
