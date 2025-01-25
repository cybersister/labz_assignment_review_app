package com.hcc.repositories;


import com.hcc.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing and managing the {@link Assignment} entity. This
 * interface provides methods for performing CRUD operations and other queries on the
 * {@link Assignment} entity.
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Optional<List<Assignment>> findByUserId(Long userId);
    Optional<Assignment> findByAssignmentId(Long assignmentId);

}
