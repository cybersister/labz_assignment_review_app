package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {

    ASSIGNMENT_1(1, "Spring Boot Service"),
    ASSIGNMENT_2(2, "Spring Boot Data JPA"),
    ASSIGNMENT_3(3, "Spring Boot PostgreSQL"),
    ASSIGNMENT_4(4, "Docker Compose Setup"),
    ASSIGNMENT_5(5, "React Frontend Hooks"),
    ASSIGNMENT_6(6, "Python Problems"),
    ASSIGNMENT_7(7, "Assembly Boot Sector Hello World"),
    ASSIGNMENT_8(8, "CTF Buffer Overflow"),
    ASSIGNMENT_9(9, "Docker Build"),
    ASSIGNMENT_10(10, "AWS Buckets"),
    ASSIGNMENT_11(11, "HashMaps and Sets"),
    ASSIGNMENT_12(12, "Computations Counting"),
    ASSIGNMENT_13(13, "Data Driven Websites"),
    ASSIGNMENT_14(14, "SQL Reporting");

    private int assignmentNumber;
    private String assignmentName;

    AssignmentEnum (int assignmentNumber, String assignmentName) {
        this.assignmentNumber = assignmentNumber;
        this.assignmentName = assignmentName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

}
