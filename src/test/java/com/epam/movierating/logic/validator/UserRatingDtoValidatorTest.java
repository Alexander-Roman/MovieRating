package com.epam.movierating.logic.validator;

import com.epam.movierating.model.dto.UserRatingDto;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserRatingDtoValidatorTest {
    private static final long VALID_ID = 1L;
    private static final int VALID_ASSESSMENT = 5;
    private static final UserRatingDto VALID_USER_RATING_DTO = new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, VALID_ASSESSMENT);

    private final UserRatingDtoValidator userRatingDtoValidator = new UserRatingDtoValidator();

    @Test
    public void testIsValidShouldReturnTrueWhenUserRatingDtoValid() {
        //given
        //when
        boolean actual = userRatingDtoValidator.isValid(VALID_USER_RATING_DTO);
        //then
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "invalidUserRatingDtoProvider")
    public void testIsValidShouldReturnFalseWhenUserRatingDtoInvalid(UserRatingDto userRatingDto) {
        //given
        //when
        boolean actual = userRatingDtoValidator.isValid(userRatingDto);
        //then
        Assert.assertFalse(actual);
    }

    @DataProvider(name = "invalidUserRatingDtoProvider")
    public Object[][] provideInvalidUserRatingDtoObjects() {
        return new UserRatingDto[][]{
                {null},
                {new UserRatingDto(0L, VALID_ID, VALID_ID, VALID_ASSESSMENT)},
                {new UserRatingDto(-1L, VALID_ID, VALID_ID, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, null, VALID_ID, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, 0L, VALID_ID, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, -1L, VALID_ID, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, VALID_ID, null, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, VALID_ID, 0L, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, VALID_ID, -1L, VALID_ASSESSMENT)},
                {new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, null)},
                {new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, 0)},
                {new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, -1)},
                {new UserRatingDto(VALID_ID, VALID_ID, VALID_ID, 11)}
        };
    }
}
