package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.TaskAssign;


@Repository
public interface TaskAssignRepository extends JpaRepository<TaskAssign, Long> {

}
