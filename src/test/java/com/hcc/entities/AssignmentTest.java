package com.hcc.entities;

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
        String status = "Submitted";
        Integer number = 1;
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
        Assignment assignment = new Assignment(status, number, githubUrl, branch,
                reviewVideoUrl, user, reviewer);

        // then
//        assertNotNull(assignment.getId(), "The id for user should not be null.");
        // the <@GeneratedValue> annotation, combined with <@Id>, instructs jpa to
        //  automatically generate the value for the <id> field ... however, the value will
        //  only be generated once the entity is persisted to the database
        assertEquals(assignment.getStatus(), status);
        assertEquals(assignment.getNumber(), number);
        assertEquals(assignment.getGithubUrl(), githubUrl);
        assertEquals(assignment.getBranch(), branch);
        assertEquals(assignment.getReviewVideoUrl(), reviewVideoUrl);
    }

}
