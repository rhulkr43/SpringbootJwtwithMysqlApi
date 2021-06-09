package com.project.Controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Services.TaskAssignService;
import com.project.Services.TaskService;
import com.project.entity.Task;
import com.project.entity.TaskAssign;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskAssignService taskAssignService;
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Task task){
		taskService.save(task);
		return new ResponseEntity<Object>("Task Add  Successfull",HttpStatus.OK);
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ResponseEntity<Object> getTaskList(){
		return new ResponseEntity<Object>(taskService.getAllTask(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listbyUserid/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> getTaskByUserid(@PathVariable("id") Integer id){
		List<Task> list=taskService.getAllTask();
		list.stream().filter(x->x.getUser_id().equals(id.intValue())).collect(Collectors.toList());
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/taskassignlist",method=RequestMethod.GET)
	public ResponseEntity<Object> getTaskAssignEntity(){
		return new ResponseEntity<Object>(taskAssignService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/taskassign/save",method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody TaskAssign taskAssign){
		
		 TaskAssign taskAssign2=taskAssignService.save(taskAssign);
		 return new ResponseEntity<Object>(taskAssign2,HttpStatus.OK);
		 
	}
	
}
