package com.hcc.dto;

import com.hcc.entities.Assignment;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentResponseDTOTest {


    /**
     * Verifies that the AssignmentResponseDTO class correctly maps the properties from an Assignment
     * entity to a DTO object.
     */
    @Test
    public void AssignmentResponseDTO_mapsCorrectly() {
        // given
        LocalDate localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        User user = new User(localDate, "maddiodie", "123456789", authorityList);

        User reviewer = new User(localDate, "professor", "987654321", authorityList);

        Assignment assignment = new Assignment("Pending Submission", 144,
                "https://github.com/madeup/assignment/slay/", "ticket1",
                "https://www.loom.com/loom/video/for/you", user, reviewer);


        // when
        AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO(assignment);

        // then
        assertEquals(assignmentResponseDTO.getUsername(), assignment.getUser().getUsername());
        assertEquals(assignmentResponseDTO.getBranch(), assignment.getBranch());
        assertEquals(assignmentResponseDTO.getNumber(), assignment.getNumber());
        assertEquals(assignmentResponseDTO.getGithubUrl(), assignment.getGithubUrl());
        assertEquals(assignmentResponseDTO.getStatus(), assignment.getStatus());
        assertEquals(assignmentResponseDTO.getReviewVideoUrl(), assignment.getReviewVideoUrl());
    }



}
