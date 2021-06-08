package com.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.TaskRepository;
import com.project.entity.Task;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	
	public void save(Task task) {
		taskRepository.save(task);
	}
	
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}
	
	public Task getTask(Long id) {
		return taskRepository.findById(id).get();
	}
	
	
	

}
