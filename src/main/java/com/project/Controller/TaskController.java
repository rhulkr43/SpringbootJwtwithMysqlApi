package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Services.TaskService;
import com.project.entity.Task;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
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
		return new ResponseEntity<Object>("ok",HttpStatus.OK);
	}
}
