package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * This method searches the database for a list of {@link Assignment}s from a
     * {@link User} based on a given {@link User} ID.
     * @param userId the primary key of {@link User} objects that will be used to search for
     *               a list of {@link Assignment}s for a {@link User} in the database
     * @return the list of {@link Assignment}s that were searched for
     */
    public List<Assignment> getAssignmentsByUserId(Long userId) {
        Optional<List<Assignment>> assignments = assignmentRepository.findByUserId(userId);

        if (assignments.isEmpty()) {
            return Collections.emptyList();
        }

        return assignments.get();
    }

    /**
     * Searches the database for an {@link Assignment} based on the given
     * {@code assignmentId}. It will throw an {@link EntityNotFoundException} if no
     * {@link Assignment} is found using the given {@code assignmentId}.
     * @param assignmentId the primary key of {@link Assignment} object that will be used to
     *                     search for an {@link Assignment} in the database
     * @return the {@link Assignment} that was searched for
     */
    public Assignment getAssignmentByAssignmentId(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository
                .findByAssignmentId(assignmentId);

        if (assignment.isEmpty()) {
            throw new EntityNotFoundException("Assignment with ID "
                    + assignmentId.toString() + " not found.");
        }

        return assignment.get();
    }

    /**
     * Updates an {@link Assignment} in the database by searching for an existing
     * {@link Assignment} using the given {@code assignmentId}. If an assignment with the
     * specified ID does not exist, an {@link EntityNotFoundException} is thrown.
     * @param assignmentId the ID of the {@link} Assignment to update
     * @param updatedAssignment the updated {@link Assignment} object containing the new
     *                          values to save
     * @return the updated {@link Assignment} after it has been saved to the database
     */
    public Assignment updateAssignmentByAssignmentId(Long assignmentId,
                                                 Assignment updatedAssignment) {
        Optional<Assignment> existingAssignment = assignmentRepository
                .findByAssignmentId(assignmentId);

        if (existingAssignment.isEmpty()) {
            throw new EntityNotFoundException("Assignment with ID "
                    + assignmentId.toString() + " not found.");
        }

        assignmentRepository.save(updatedAssignment);

        return updatedAssignment;
        // the save method is built into JpaRepository
    }

    /**
     * Creates a new {@link Assignment} entity in the database with the given
     * {@link Assignment}.
     * @param newAssignment the new {@link Assignment} that is being persisted
     * @return the new {@link Assignment} that is being saved
     */
    public Assignment saveNewAssignment(Assignment newAssignment) {
        assignmentRepository.save(newAssignment);

        return newAssignment;
    }

}
