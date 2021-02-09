package com.epam.movierating.logic.validator;

import com.epam.movierating.model.dto.CommentDto;
import com.epam.movierating.model.entity.Account;

import java.time.LocalDateTime;

public class CommentDtoValidator implements Validator<CommentDto> {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MAX_TEXT_LENGTH = 255;

    @Override
    public boolean isValid(CommentDto commentDto) {
        if (commentDto == null) {
            return false;
        }
        Long id = commentDto.getId();
        Long movieId = commentDto.getMovieId();
        Account account = commentDto.getAuthor();
        LocalDateTime dateTime = commentDto.getDateTime();
        String text = commentDto.getText();
        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }
        if (movieId == null || account == null || dateTime == null || text == null) {
            return false;
        }
        if (text.trim().isEmpty()) {
            return false;
        }
        return text.length() <= MAX_TEXT_LENGTH;
    }
}
