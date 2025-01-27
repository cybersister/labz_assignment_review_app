package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.services.AssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentControllerTest {

    @Mock
    private AssignmentService assignmentService;
    @InjectMocks
    private AssignmentController assignmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAssignmentByUserId_testEndpoint_statusOK() {
        // given
        Long userId = 1L;
        List<Assignment> assignments = Arrays.asList(new Assignment(), new Assignment());

        when(assignmentService.getAssignmentsByUserId(userId)).thenReturn(assignments);

        // when
        ResponseEntity<List<Assignment>> response =
                assignmentController.getAssignmentsByUserId(userId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Response HttpStatus's should be the same.");
        assertEquals(assignments, response.getBody(),
                "Response Bodies should be the same.");

        verify(assignmentService, times(1))
                .getAssignmentsByUserId(userId);
    }

    @Test
    public void getAssignmentByAssignmentId_testEndpoint_statusOK() {
        // given
        Long assignmentId = 2L;
        Assignment assignment = new Assignment();

        when(assignmentService.getAssignmentByAssignmentId(assignmentId))
                .thenReturn(assignment);

        // when
        ResponseEntity<Assignment> response = assignmentController
                .getAssignmentByAssignmentId(assignmentId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Response HttpStatus's should be the same.");
        assertEquals(assignment, response.getBody(),
                "Response Bodies should be the same.");
        verify(assignmentService, times(1))
                .getAssignmentByAssignmentId(assignmentId);
    }

    @Test
    public void updateAssignmentByAssignmentId_testEndpoint_statusOK() {
        // given
        Long assignmentId = 3L;
        Assignment updatedAssignment = new Assignment();

        when(assignmentService.updateAssignmentByAssignmentId(assignmentId,
                updatedAssignment)).thenReturn(updatedAssignment);

        // when
        ResponseEntity<Assignment> response = assignmentController
                .updateAssignmentById(assignmentId, updatedAssignment);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Response HttpStatus's should be the same.");
        assertEquals(updatedAssignment, response.getBody(),
                "Response Bodies should be the same.");
        verify(assignmentService, times(1))
                .updateAssignmentByAssignmentId(assignmentId, updatedAssignment);
    }

    @Test
    public void saveNewAssignment_testEndpoint_statusOK() {
        // given
        Assignment assignment = new Assignment();

        when(assignmentService.saveNewAssignment(assignment)).thenReturn(assignment);

        // when
        ResponseEntity<Assignment> response = assignmentController
                .saveNewAssignment(assignment);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Response HttpStatus's should be the same.");
        assertEquals(assignment, response.getBody(),
                "Response Bodies should be the same.");
        verify(assignmentService, times(1))
                .saveNewAssignment(assignment);
    }

}
