package com.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.TaskAssignRepository;
import com.project.entity.TaskAssign;

@Service
public class TaskAssignService {

	@Autowired
	private TaskAssignRepository taskAssignRepository;
	
	
	public List<TaskAssign> getAll(){
		return taskAssignRepository.findAll();
	}
	
	
	public TaskAssign save(TaskAssign taskAssign) {
		taskAssignRepository.save(taskAssign);
		return taskAssign;
	}
}
