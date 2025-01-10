package com.hcc.entities;

public class Assignment {

    private Long id;
    private String status;
    private Integer number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    private User user;

    public Assignment() {}
    // NO-ARGS CONSTRUCTOR

    public Assignment(String status, Integer number, String githubUrl, String branch,
                      String reviewVideoUrl, User user) {
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
    }
    // ALL-ARGS CONSTRUCTOR EXCEPT ID



}
