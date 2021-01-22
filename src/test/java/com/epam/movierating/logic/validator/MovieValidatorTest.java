package com.epam.movierating.logic.validator;

import com.epam.movierating.model.entity.Movie;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MovieValidatorTest {

    private static final long VALID_ID = 1L;
    private static final String VALID_TITLE = "title";
    private static final String VALID_DIRECTOR = "director";
    private static final int VALID_RELEASE_YEAR = 2021;
    private static final String VALID_SYNOPSIS = "synopsis";
    private static final String VALID_POSTER_PATH = "posterPath";
    private static final double VALID_RATING = 5.6;
    private static final Movie VALID_MOVIE = new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING);

    private static final int MAX_TITLE_LENGTH = 255;
    private static final int MAX_DIRECTOR_LENGTH = 45;
    private static final int MAX_SYNOPSIS_LENGTH = 1000;
    private static final int MAX_POSTER_PATH_LENGTH = 255;

    private final MovieValidator movieValidator = new MovieValidator();

    @Test
    public void testIsValidShouldReturnTrueWhenMovieValid() {
        //given
        //when
        boolean actual = movieValidator.isValid(VALID_MOVIE);
        //then
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "invalidMovieProvider")
    public void testIsValidShouldReturnFalseWhenMovieInvalid(Movie invalidMovie) {
        //given
        //when
        boolean actual = movieValidator.isValid(invalidMovie);
        //then
        Assert.assertFalse(actual);
    }

    @DataProvider(name = "invalidMovieProvider")
    public Object[][] provideInvalidMovieObjects() {
        String titleLengthExceeded = buildStringExceedingLength(MAX_TITLE_LENGTH);
        String directorLengthExceeded = buildStringExceedingLength(MAX_DIRECTOR_LENGTH);
        String synopsisLengthExceeded = buildStringExceedingLength(MAX_SYNOPSIS_LENGTH);
        String posterPathLengthExceeded = buildStringExceedingLength(MAX_POSTER_PATH_LENGTH);

        return new Movie[][]{
                {null},
                {new Movie(0L, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(-1L, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, null, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, "", VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, "  ", VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, "\n", VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, " \n ", VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, titleLengthExceeded, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, "", VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, "  ", VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, "\n", VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, " \n ", VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, directorLengthExceeded, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, 1894, VALID_SYNOPSIS, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, "", VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, "  ", VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, "\n", VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, " \n ", VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, synopsisLengthExceeded, VALID_POSTER_PATH, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, "", VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, "  ", VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, "\n", VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, " \n ", VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, posterPathLengthExceeded, VALID_RATING)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, -0.1)},
                {new Movie(VALID_ID, VALID_TITLE, VALID_DIRECTOR, VALID_RELEASE_YEAR, VALID_SYNOPSIS, VALID_POSTER_PATH, 10.1)}
        };
    }

    private String buildStringExceedingLength(int length) {
        StringBuilder stringBuilder = new StringBuilder("The length of the string exceeds " + length + " characters:");
        for (int i = 0; i <= length; i++) {
            stringBuilder.append(".");
        }
        return stringBuilder.toString();
    }
}
