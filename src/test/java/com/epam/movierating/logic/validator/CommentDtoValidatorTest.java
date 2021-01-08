package com.epam.movierating.logic.validator;

import com.epam.movierating.model.Role;
import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class CommentDtoValidatorTest {

    private static final long VALID_ID = 1L;
    private static final String VALID_TEXT = "text";
    private static final Account ACCOUNT = new Account(1L, "userName", null, Role.USER, false);
    private static final CommentDto VALID_COMMENT_DTO = new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), VALID_TEXT);

    private final CommentDtoValidator commentDtoValidator = new CommentDtoValidator();

    @Test
    public void testIsValidShouldReturnTrueWhenCommentDtoValid() {
        //given
        //when
        boolean actual = commentDtoValidator.isValid(VALID_COMMENT_DTO);
        //then
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "invalidCommentDtoProvider")
    public void testIsValidShouldReturnFalseWhenCommentDtoInvalid(CommentDto invalidCommentDto) {
        //given
        //when
        boolean actual = commentDtoValidator.isValid(invalidCommentDto);
        //then
        Assert.assertFalse(actual);
    }

    @DataProvider(name = "invalidCommentDtoProvider")
    public Object[][] provideInvalidCommentDtoObjects() {
        StringBuilder stringBuilder = new StringBuilder("The length of the text exceeds 255 characters:");
        for (int i = 0; i < 256; i++) {
            stringBuilder.append(".");
        }
        String textLengthExceeded = stringBuilder.toString();

        return new CommentDto[][]{
                {null},
                {new CommentDto(0L, VALID_ID, ACCOUNT, LocalDateTime.now(), VALID_TEXT)},
                {new CommentDto(-1L, VALID_ID, ACCOUNT, LocalDateTime.now(), VALID_TEXT)},
                {new CommentDto(VALID_ID, null, ACCOUNT, LocalDateTime.now(), VALID_TEXT)},
                {new CommentDto(VALID_ID, VALID_ID, null, LocalDateTime.now(), VALID_TEXT)},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, null, VALID_TEXT)},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), null)},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), "")},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), "   ")},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), "\n")},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), " \n ")},
                {new CommentDto(VALID_ID, VALID_ID, ACCOUNT, LocalDateTime.now(), textLengthExceeded)}
        };
    }
}
