package com.epam.movierating.entity;

import java.util.Objects;

public final class UserRating {

    private final int assessment;
    private final Movie assessedMovie;
    private final Account assessor;

    public UserRating(int assessment, Movie assessedMovie, Account assessor) {
        this.assessment = assessment;
        this.assessedMovie = assessedMovie;
        this.assessor = assessor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRating that = (UserRating) o;
        return assessment == that.assessment &&
                Objects.equals(assessedMovie, that.assessedMovie) &&
                Objects.equals(assessor, that.assessor);
    }

    @Override
    public int hashCode() {
        int result = assessment;
        result = 31 * result + (assessedMovie != null ? assessedMovie.hashCode() : 0);
        result = 31 * result + (assessor != null ? assessor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "assessment=" + assessment +
                ", assessedMovie=" + assessedMovie +
                ", assessor=" + assessor +
                '}';
    }
}
