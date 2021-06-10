package com.project.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.TaskAssignRepository;
import com.project.entity.TaskAssign;

@Service
public class TaskAssignService {

	@Autowired
	private TaskAssignRepository taskAssignRepository;
	
	List<TaskAssign> list=new ArrayList<>();
	
	public List<TaskAssign> getAll(){
		this.list=taskAssignRepository.findAll();
		return this.list;
	}
	
	public List<TaskAssign> getAllUSerAssigns(Integer user_id){
		List<TaskAssign> list2=new ArrayList<>();
		list2=this.list.stream().filter(x->x.getUser_id().equals(user_id)).collect(Collectors.toList());
		return this.list;
	}
	
	public TaskAssign save(TaskAssign taskAssign) {
		taskAssignRepository.save(taskAssign);
		return taskAssign;
	}
}
