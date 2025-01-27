package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Searches for all {@link Assignment}s associated with a given {@code userId}.
     * @param userId the primary key of {@link User} objects that will be used to search for
     *               a list of {@link Assignment}s for a {@link User} in the database
     * @return the list of {@link Assignment}s that were searched for
     */
    public List<Assignment> getAssignmentsByUserId(Long userId) {
        return assignmentRepository.findByUserId(userId).orElse(Collections.emptyList());
    }

    /**
     * Searches for an {@link Assignment} based on the given {@code assignmentId}. It will
     * throw an {@link AssignmentNotFoundException} if no {@link Assignment} is found.
     * @param assignmentId the primary key of {@link Assignment} object that will be used to
     *                     search for an {@link Assignment} in the database
     * @return the {@link Assignment} that was searched for
     */
    public Assignment getAssignmentByAssignmentId(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository
                .findByAssignmentId(assignmentId);

        if (assignment.isEmpty()) {
            throw new AssignmentNotFoundException("Assignment with ID "
                    + assignmentId.toString() + " not found.");
        }

        return assignment.get();
    }

    /**
     * Updates an {@link Assignment} using the given {@code assignmentId} and updated
     * {@link Assignment}. If an assignment with the specified ID does not exist, an
     * {@link AssignmentNotFoundException} is thrown.
     * @param assignmentId the ID of the {@link} Assignment to update
     * @param updatedAssignment the updated {@link Assignment} object containing the new
     *                          values to save
     * @return the updated {@link Assignment} after it has been saved to the database
     */
    public Assignment updateAssignmentByAssignmentId(Long assignmentId,
                                                 Assignment updatedAssignment) {
        Assignment existingAssignment = assignmentRepository
                .findByAssignmentId(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException("Assignment with ID "
                        + assignmentId.toString() + " not found."));

        existingAssignment.setUser(updatedAssignment.getUser());
        existingAssignment.setNumber(updatedAssignment.getNumber());
        existingAssignment.setBranch(updatedAssignment.getBranch());
        existingAssignment.setStatus(updatedAssignment.getStatus());
        existingAssignment.setGithubUrl(updatedAssignment.getGithubUrl());
        existingAssignment.setCodeReviewer(updatedAssignment.getCodeReviewer());
        existingAssignment.setReviewVideoUrl(updatedAssignment.getReviewVideoUrl());

        return assignmentRepository.save(existingAssignment);
        // the save method is built into JpaRepository
    }

    /**
     * Creates a new {@link Assignment} entry with the given {@link Assignment}.
     * @param newAssignment the new {@link Assignment} that is being persisted
     * @return the new {@link Assignment} that is being saved
     */
    public Assignment saveNewAssignment(Assignment newAssignment) {
        Long newAssignmentId = newAssignment.getId();

        if (newAssignmentId != null && assignmentRepository.existsById(newAssignmentId)) {
            throw new IllegalArgumentException("Assignment with ID " + newAssignmentId
                    + " already exists.");
        }

        assignmentRepository.save(newAssignment);

        return newAssignment;
    }

}
