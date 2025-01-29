package com.hcc.entities;

import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @ElementCollection(targetClass = AssignmentEnum.class)
    @CollectionTable(name = "assignment_numbers_enums",
            joinColumns = @JoinColumn(name = "assignment_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_numbers_enums")
    private List<AssignmentEnum> assignmentNumbersEnums;

    @ElementCollection(targetClass = AssignmentStatusEnum.class)
    @CollectionTable(name = "assignment_status_enums",
            joinColumns = @JoinColumn(name = "assignment_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_status_enums")
    private List<AssignmentStatusEnum> assignmentStatusEnums;

    @Column(name = "github_url")
    private String githubUrl;
    @Column(name = "branch")
    private String branch;
    @Column(name = "code_review_video_url")
    private String reviewVideoUrl;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "code_reviewer_id")
    private User codeReviewer;

    public Assignment() {}
    // NO-ARGS CONSTRUCTOR

    public Assignment(List<AssignmentEnum> assignmentNumbersEnums,
                      List<AssignmentStatusEnum> assignmentStatusEnums, String githubUrl,
                      String branch, String reviewVideoUrl, User user, User codeReviewer) {
        this.assignmentNumbersEnums = assignmentNumbersEnums;
        this.assignmentStatusEnums = assignmentStatusEnums;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }
    // ALL-ARGS CONSTRUCTOR EXCEPT ID

    public Long getId() {
        return assignmentId;
    }

    public List<AssignmentEnum> getAssignmentNumbersEnums() {
        return assignmentNumbersEnums;
    }

    public List<AssignmentStatusEnum> getAssignmentStatusEnums() {
        return assignmentStatusEnums;
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

    public User getUser() {
        return user;
    }

    public User getCodeReviewer() {
        return codeReviewer;
    }

    public void setCodeReviewer(User codeReviewer) {
        this.codeReviewer = codeReviewer;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setAssignmentNumbersEnums(List<AssignmentEnum> assignmentNumbersEnums) {
        this.assignmentNumbersEnums = assignmentNumbersEnums;
    }

    public void setAssignmentStatusEnums(List<AssignmentStatusEnum> assignmentStatusEnums) {
        this.assignmentStatusEnums = assignmentStatusEnums;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
