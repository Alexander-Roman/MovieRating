package com.epam.movierating.model.dto;

import com.epam.movierating.model.Identifiable;

import java.util.Objects;

public class UserRatingDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long assessedId;
    private final Long assessorId;
    private final Integer assessment;

    public UserRatingDto(Long id, Long assessedId, Long assessorId, Integer assessment) {
        this.id = id;
        this.assessedId = assessedId;
        this.assessorId = assessorId;
        this.assessment = assessment;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getAssessedId() {
        return assessedId;
    }

    public Long getAssessorId() {
        return assessorId;
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
        UserRatingDto that = (UserRatingDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(assessedId, that.assessedId) &&
                Objects.equals(assessorId, that.assessorId) &&
                Objects.equals(assessment, that.assessment);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assessedId != null ? assessedId.hashCode() : 0);
        result = 31 * result + (assessorId != null ? assessorId.hashCode() : 0);
        result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", assessedId=" + assessedId +
                ", assessorId=" + assessorId +
                ", assessment=" + assessment +
                '}';
    }
}
