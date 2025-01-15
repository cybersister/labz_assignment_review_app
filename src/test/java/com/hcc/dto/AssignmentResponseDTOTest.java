package com.hcc.dto;

import com.hcc.entities.Assignment;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AssignmentResponseDTOTest {

    private Assignment assignment;
    private User user;
    private User codeReviewer;
    private LocalDate localDate;

    @BeforeEach
    public void setup() {
        localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        user = new User(localDate, "maddiodie", "password", authorityList);

        codeReviewer = new User(localDate, "prince_prof", "password",
                authorityList);
    }

    @Test
    public void AssignmentResponseDTO_mapsCorrectly() {
        // given
        assignment = new Assignment("Pending Submission", 1,
                "https://github.com/madeup/assignment/slay/", "main",
                "https://youtube.com/review/slay/assignment", user,
                codeReviewer);

        // when
        AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO(assignment);

        // then
        assertEquals(assignmentResponseDTO.getUsername(), assignment.getUser()
                .getUsername());
        assertEquals(assignmentResponseDTO.getBranch(), assignment.getBranch());
        assertEquals(assignmentResponseDTO.getNumber(), assignment.getNumber());
        assertEquals(assignmentResponseDTO.getGithubUrl(), assignment.getGithubUrl());
        assertEquals(assignmentResponseDTO.getStatus(), assignment.getStatus());
        assertEquals(assignmentResponseDTO.getReviewVideoUrl(), assignment
                .getReviewVideoUrl());
    }

    @Test
    public void AssignmentResponseDTO_nullCodeReviewer() {
        // given
        assignment = new Assignment("Pending Submission", 1,
                "https://github.com/madeup/assignment/slay/", "main",
                "https://youtube.com/review/slay/assignment", user,
                null);

        // when
        AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO(assignment);

        // then
        assertEquals(assignmentResponseDTO.getUsername(), assignment.getUser()
                .getUsername());
        assertEquals(assignmentResponseDTO.getBranch(), assignment.getBranch());
        assertEquals(assignmentResponseDTO.getNumber(), assignment.getNumber());
        assertEquals(assignmentResponseDTO.getGithubUrl(), assignment.getGithubUrl());
        assertEquals(assignmentResponseDTO.getStatus(), assignment.getStatus());
        assertEquals(assignmentResponseDTO.getReviewVideoUrl(), assignment
                .getReviewVideoUrl());
        assertNull(assignmentResponseDTO.getCodeReviewerUsername());
    }

}
