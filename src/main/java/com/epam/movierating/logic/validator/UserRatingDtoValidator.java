package com.epam.movierating.logic.validator;

import com.epam.movierating.model.dto.UserRatingDto;

public class UserRatingDtoValidator implements Validator<UserRatingDto> {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ASSESSMENT_VALUE = 1;
    private static final int MAX_ASSESSMENT_VALUE = 10;

    @Override
    public boolean isValid(UserRatingDto userRatingDto) {
        if (userRatingDto == null) {
            return false;
        }

        Long id = userRatingDto.getId();
        Long assessedId = userRatingDto.getAssessedId();
        Long assessorId = userRatingDto.getAssessorId();
        Integer assessment = userRatingDto.getAssessment();
        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }
        if (assessedId == null || assessorId == null || assessment == null) {
            return false;
        }
        if (assessedId < MIN_ID_VALUE || assessorId < MIN_ID_VALUE) {
            return false;
        }
        return MIN_ASSESSMENT_VALUE <= assessment && assessment <= MAX_ASSESSMENT_VALUE;
    }
}
