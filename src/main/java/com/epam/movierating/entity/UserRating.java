package com.epam.movierating.entity;

import java.util.Objects;

public final class UserRating {

    private final Movie assessedMovie;
    private final Account assessor;
    private final int assessment;

    public UserRating(Movie assessedMovie, Account assessor, int assessment) {
        this.assessedMovie = assessedMovie;
        this.assessor = assessor;
        this.assessment = assessment;
    }

    public int getAssessment() {
        return assessment;
    }

    public Movie getAssessedMovie() {
        return assessedMovie;
    }

    public Account getAssessor() {
        return assessor;
    }


}
