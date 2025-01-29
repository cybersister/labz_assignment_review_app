package com.hcc.entities;

import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    // (1) once the database is set up, test that the id generates after an Assignment object
    //  is persisted
    // (2) once the database is set up, test that the <user> variable within the Assignment
    //  object can't be defined as null

    @Test
    public void Assignment_instantiatesAnObjectCorrectly() {
        // given
        List<AssignmentStatusEnum> assignmentStatusEnums = new ArrayList<>();
        assignmentStatusEnums.add(AssignmentStatusEnum.COMPLETED);

        List<AssignmentEnum> assignmentNumbersEnums = new ArrayList<>();
        assignmentNumbersEnums.add(AssignmentEnum.ASSIGNMENT_2);

        String githubUrl = "https://github.com/madeup/assignment/slay/";
        String branch = "main";
        String reviewVideoUrl = "https://youtube.com/review/slay/assignment";

        LocalDate localDate = LocalDate.now();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority());

        User user = new User(localDate, "username", "password",
                authorityList);
        User reviewer = new User(localDate, "anotherUsername",
                "anotherPassword", authorityList);

        // when
        Assignment assignment = new Assignment(assignmentNumbersEnums, assignmentStatusEnums,
                githubUrl, branch, reviewVideoUrl, user, reviewer);

        // then
//        assertNotNull(assignment.getId(), "The id for user should not be null.");
        // the <@GeneratedValue> annotation, combined with <@Id>, instructs jpa to
        //  automatically generate the value for the <id> field ... however, the value will
        //  only be generated once the entity is persisted to the database
        assertEquals(assignment.getAssignmentStatusEnums(), assignmentStatusEnums);
        assertEquals(assignment.getAssignmentNumbersEnums(), assignmentNumbersEnums);
        assertEquals(assignment.getGithubUrl(), githubUrl);
        assertEquals(assignment.getBranch(), branch);
        assertEquals(assignment.getReviewVideoUrl(), reviewVideoUrl);
    }

}
