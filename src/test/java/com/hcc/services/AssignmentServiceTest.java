package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;
    @InjectMocks
    private AssignmentService assignmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAssignmentsByUserId_exitingUserId_returnsListOfAssignments() {
        // given
        Long userId = 1L;
        List<Assignment> assignments = Arrays.asList(new Assignment(), new Assignment());

        when(assignmentRepository.findByUserId(userId))
                .thenReturn(Optional.of(assignments));

        // when
        List<Assignment> result = assignmentService.getAssignmentsByUserId(userId);

        // then
        assertEquals(assignments, result,
                "List of Assignments should be the same.");
        verify(assignmentRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void getAssignmentByUserId_nonExistingUserId_returnsEmptyList() {
        // given
        Long badUserId = 2L;
        List<Assignment> emptyList = Collections.emptyList();

        when(assignmentRepository.findByUserId(badUserId))
                .thenReturn(Optional.of(emptyList));

        // when
        List<Assignment> result = assignmentService.getAssignmentsByUserId(badUserId);

        // then
        assertEquals(emptyList, result, "List should be empty.");
        verify(assignmentRepository, times(1))
                .findByUserId(badUserId);
    }

    @Test
    public void getAssignmentByAssignmentId_existingAssignmentId_returnsAssignment() {
        // given
        Long assignmentId = 3L;
        Assignment assignment = new Assignment();

        when(assignmentRepository.findByAssignmentId(assignmentId))
                .thenReturn(Optional.of(assignment));

        // when
        Assignment result = assignmentService.getAssignmentByAssignmentId(assignmentId);

        // then
        assertEquals(assignment, result, "Assignments should be the same.");
        verify(assignmentRepository, times(1))
                .findByAssignmentId(assignmentId);
    }

    @Test
    public void getAssignmentByAssignmentId_nonExistingAssignmentId_throwsException() {
        // given
        Long badAssignmentId = 4L;

        when(assignmentRepository.findByAssignmentId(badAssignmentId))
                .thenReturn(Optional.empty());

        // when + then
        assertThrows(AssignmentNotFoundException.class, () -> {
            assignmentService.getAssignmentByAssignmentId(badAssignmentId);
        });
        verify(assignmentRepository, times(1))
                .findByAssignmentId(badAssignmentId);
    }

    @Test
    public void updatesAssignmentByAssignmentId_existingAssignmentId_updatesAssignment() {
        // given
        Long assignmentId = 5L;
        Assignment existingAssignment = new Assignment();
        existingAssignment.setAssignmentId(assignmentId);

        User newUser = new User();
        User newCodeReviewer = new User();
        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setStatus("New Status");
        updatedAssignment.setNumber(2);
        updatedAssignment.setGithubUrl("New Github URL");
        updatedAssignment.setBranch("New Branch");
        updatedAssignment.setReviewVideoUrl("New Review Video URL");
        updatedAssignment.setUser(newUser);
        updatedAssignment.setCodeReviewer(newCodeReviewer);

        when(assignmentRepository.findByAssignmentId(assignmentId))
                .thenReturn(Optional.of(existingAssignment));
        when(assignmentRepository.save(existingAssignment)).thenReturn(existingAssignment);

        // when
        Assignment result = assignmentService
                .updateAssignmentByAssignmentId(assignmentId, updatedAssignment);

        // then
        assertEquals(newUser, result.getUser(), "New Users should be the same.");
        assertEquals(2, result.getNumber(),
                "New Numbers should be the same.");
        assertEquals("New Branch", result.getBranch(),
                "New Branches should be the same.");
        assertEquals("New Status", result.getStatus(),
                "New Statuses should be the same.");
        assertEquals("New Github URL", result.getGithubUrl(),
                "New Github URLs should be the same.");
        assertEquals(newCodeReviewer, result.getCodeReviewer(),
                "New Code Reviewers should be the same.");
        assertEquals("New Review Video URL", result.getReviewVideoUrl(),
                "New Review Video URLs should be the same.");

        verify(assignmentRepository, times(1))
                .findByAssignmentId(assignmentId);
        verify(assignmentRepository, times(1))
                .save(existingAssignment);
    }

    @Test
    public void updatesAssignmentByAssignmentId_nonExistingAssignmentId_throwsException() {
        // given
        Long nonExistingAssignmentId = 6L;
        Assignment updatedAssignment = new Assignment();

        // when + then
        assertThrows(AssignmentNotFoundException.class, () -> {
            assignmentService.updateAssignmentByAssignmentId(nonExistingAssignmentId,
                    updatedAssignment);
        });
        verify(assignmentRepository, times(1))
                .findByAssignmentId(nonExistingAssignmentId);
    }

    @Test
    public void saveNewAssignment_newAssignment_saveNewAssignment() {
        // given
        Long newAssignmentId = 7L;
        User newUser = new User();
        User newCodeReviewer = new User();

        Assignment newAssignment = new Assignment();
        newAssignment.setAssignmentId(newAssignmentId);
        newAssignment.setUser(newUser);
        newAssignment.setCodeReviewer(newCodeReviewer);
        newAssignment.setBranch("New Branch");
        newAssignment.setNumber(8008);
        newAssignment.setStatus("New Status");
        newAssignment.setReviewVideoUrl("New Review Video URL");
        newAssignment.setGithubUrl("New Github URL");

        when(assignmentRepository.existsById(newAssignmentId))
                .thenReturn(false);
        when(assignmentRepository.save(newAssignment)).thenReturn(newAssignment);

        // when
        Assignment result = assignmentService.saveNewAssignment(newAssignment);

        // then
        assertEquals(newAssignment, result, "Assignments should be the same.");
        verify(assignmentRepository, times(1))
                .existsById(newAssignmentId);
        verify(assignmentRepository, times(1))
                .save(newAssignment);
    }

    @Test
    public void saveNewAssignment_assignmentAlreadyExists_throwsIllegalArgumentException() {
        // given
        Long existingAssignmentId = 8L;
        Assignment existingAssignment = new Assignment();
        existingAssignment.setAssignmentId(existingAssignmentId);

        when(assignmentRepository.existsById(existingAssignmentId)).thenReturn(true);

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            assignmentService.saveNewAssignment(existingAssignment);
        });
        verify(assignmentRepository, times(1))
                .existsById(existingAssignmentId);
    }

}
