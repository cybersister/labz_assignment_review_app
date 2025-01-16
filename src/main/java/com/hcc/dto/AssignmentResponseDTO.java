package com.hcc.dto;

import com.hcc.entities.Assignment;
import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

/**
 * A data transfer object for the Assignment entity. The main purpose is to transfer data
 * between layers of the application, including controllers and views. This class specifically
 * provides a simplified version of the Assignment object that has been tailored for client
 * consumption. This keeps the business logic separate from the data presentation logic.
 */
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
        // ternary operator ... if the <assignment.getCodeReviewer()> is not null, then
        //  assign it the value <assignment.getCodeReviewer().getUsername()> ... otherwise,
        //  if the <assignment.getCodeReviewer()> is null, assign it a value of <null>
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

    public String getCodeReviewerUsername() {
        return codeReviewerUsername;
    }

    public AssignmentEnum[] getAssignmentEnums() {
        return assignmentEnums;
    }

    public AssignmentStatusEnum[] getAssignmentStatusEnums() {
        return statusEnums;
    }

}
