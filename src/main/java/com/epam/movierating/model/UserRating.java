package com.epam.movierating.model;

import java.util.Objects;

public final class UserRating extends Identifiable {

    private final Movie assessed;
    private final Account assessor;
    private final int assessment;

    public UserRating(Long id, Movie assessed, Account assessor, int assessment) {
        super(id);
        this.assessed = assessed;
        this.assessor = assessor;
        this.assessment = assessment;
    }

    public Movie getAssessed() {
        return assessed;
    }

    public Account getAssessor() {
        return assessor;
    }

    public int getAssessment() {
        return assessment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserRating that = (UserRating) o;
        return assessment == that.assessment &&
                Objects.equals(assessed, that.assessed) &&
                Objects.equals(assessor, that.assessor);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (assessed != null ? assessed.hashCode() : 0);
        result = 31 * result + (assessor != null ? assessor.hashCode() : 0);
        result = 31 * result + assessment;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", assessedMovie=" + assessed +
                ", assessor=" + assessor +
                ", assessment=" + assessment +
                '}';
    }
}
