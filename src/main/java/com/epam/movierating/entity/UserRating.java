package com.epam.movierating.entity;

import java.util.Objects;

public final class UserRating extends Identifiable {

    private final Movie assessedMovie;
    private final Account assessor;
    private final int assessment;

    public UserRating(Long id, Movie assessedMovie, Account assessor, int assessment) {
        super(id);
        this.assessedMovie = assessedMovie;
        this.assessor = assessor;
        this.assessment = assessment;
    }

    public Movie getAssessedMovie() {
        return assessedMovie;
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
                Objects.equals(assessedMovie, that.assessedMovie) &&
                Objects.equals(assessor, that.assessor);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (assessedMovie != null ? assessedMovie.hashCode() : 0);
        result = 31 * result + (assessor != null ? assessor.hashCode() : 0);
        result = 31 * result + assessment;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", assessedMovie=" + assessedMovie +
                ", assessor=" + assessor +
                ", assessment=" + assessment +
                '}';
    }
}
