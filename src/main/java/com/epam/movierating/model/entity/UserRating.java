package com.epam.movierating.model.entity;

import com.epam.movierating.model.Identifiable;

import java.util.Objects;

public final class UserRating implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Movie assessed;
    private final Account assessor;
    private final Integer assessment;

    public UserRating(Long id, Movie assessed, Account assessor, Integer assessment) {
        this.id = id;
        this.assessed = assessed;
        this.assessor = assessor;
        this.assessment = assessment;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Movie getAssessed() {
        return assessed;
    }

    public Account getAssessor() {
        return assessor;
    }

    public Integer getAssessment() {
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
        UserRating that = (UserRating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(assessed, that.assessed) &&
                Objects.equals(assessor, that.assessor) &&
                Objects.equals(assessment, that.assessment);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assessed != null ? assessed.hashCode() : 0);
        result = 31 * result + (assessor != null ? assessor.hashCode() : 0);
        result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", assessed=" + assessed +
                ", assessor=" + assessor +
                ", assessment=" + assessment +
                '}';
    }
}
