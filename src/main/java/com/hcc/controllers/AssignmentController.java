package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUserId(@RequestParam
                                                                     Long userId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByUserId(userId);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}") // 'id' is the path variable as it's part of the path
    public ResponseEntity<Assignment> getAssignmentByAssignmentId(@PathVariable
                                                                      Long assignmentId) {
        Assignment assignment = assignmentService.getAssignmentByAssignmentId(assignmentId);
        return ResponseEntity.ok(assignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignmentById(
            @PathVariable Long assignmentId, @RequestBody Assignment updatedAssignment) {
        Assignment assignment = assignmentService.updateAssignmentByAssignmentId(
                assignmentId, updatedAssignment);
        return ResponseEntity.ok(updatedAssignment);
    }

    @PostMapping
    public ResponseEntity<Assignment> saveNewAssignment(@RequestBody
                                                           Assignment newAssignment) {
        Assignment savedAssignment = assignmentService.saveNewAssignment(newAssignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignment);
    }

}
