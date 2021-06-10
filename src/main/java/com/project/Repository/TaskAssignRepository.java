package com.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.project.entity.TaskAssign;
import com.project.entity.User;


@Repository

public interface TaskAssignRepository extends JpaRepository<TaskAssign, Long> {

	
}
