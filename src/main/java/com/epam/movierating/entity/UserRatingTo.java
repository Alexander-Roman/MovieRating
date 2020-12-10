package com.epam.movierating.entity;

public class UserRatingTo extends Identifiable {

    private final Long assessedId;
    private final Long assessorId;
    private final int assessment;

    public UserRatingTo(Long id, Long assessedId, Long assessorId, int assessment) {
        super(id);
        this.assessedId = assessedId;
        this.assessorId = assessorId;
        this.assessment = assessment;
    }

    public Long getAssessedId() {
        return assessedId;
    }

    public Long getAssessorId() {
        return assessorId;
    }

    public int getAssessment() {
        return assessment;
    }
}
