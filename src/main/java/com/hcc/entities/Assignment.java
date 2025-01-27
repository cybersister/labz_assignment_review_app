package com.hcc.entities;

import javax.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;
    @Transient
    private String status;
    @Column(name = "number")
    private Integer number;
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

    public Assignment(String status, Integer number, String githubUrl, String branch,
                      String reviewVideoUrl, User user, User codeReviewer) {
        this.status = status;
        this.number = number;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
