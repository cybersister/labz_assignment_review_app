package com.hcc.dto;

import com.hcc.entities.Assignment;
import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

public class AssignmentResponseDTO {

    private final Long id;
    private final String status;
    private final Integer number;
    private final String githubUrl;
    private final String branch;
    private final String reviewVideoUrl;
    private final String username;
    private final String codeReviewerUsername;
    private final AssignmentEnum[] assignmentEnums;
    private final AssignmentStatusEnum[] statusEnums;

    public AssignmentResponseDTO(Assignment assignment) {
        this.id = assignment.getId();
        this.status = assignment.getStatus();
        this.number = assignment.getNumber();
        this.githubUrl = assignment.getGithubUrl();
        this.branch = assignment.getBranch();
        this.reviewVideoUrl = assignment.getReviewVideoUrl();
        this.username = assignment.getUser().getUsername();
        this.codeReviewerUsername = assignment.getCodeReviewer()
                != null ? assignment.getCodeReviewer().getUsername() : null;
        this.assignmentEnums = AssignmentEnum.values();
        this.statusEnums = AssignmentStatusEnum.values();
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Integer getNumber() {
        return number;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public String getUsername() {
        return username;
    }

    public AssignmentEnum[] getAssignmentEnums() {
        return assignmentEnums;
    }

    public AssignmentStatusEnum[] getAssignmentStatusEnums() {
        return statusEnums;
    }

}
