package com.project.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
import com.project.Services.UserServiceImp;
import com.project.entity.Task;
import com.project.entity.TaskAssign;
import com.project.entity.User;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskAssignService taskAssignService;
	@Autowired
	private UserServiceImp userServiceImp;
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
		List<Task> list2=list.stream().filter(x->x.getUser_id().equals(id)).collect(Collectors.toList());
		return new ResponseEntity<Object>(list2,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/taskassignlist",method=RequestMethod.GET)
	public ResponseEntity<Object> getTaskAssignEntity(){
		return new ResponseEntity<Object>(taskAssignService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/taskassignlist/{userid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getTaskAssignUserEntity(@PathVariable("userid") Integer userid){
		List<TaskAssign> list=taskAssignService.getAll();
		List<TaskAssign> list2=list.stream().filter(x->x.getUser_id().equals(userid)).collect(Collectors.toList());
		return new ResponseEntity<Object>(list2,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/taskassign/save",method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody TaskAssign taskAssign){
		
		 TaskAssign taskAssign2=taskAssignService.save(taskAssign);
		 return new ResponseEntity<Object>(taskAssign2,HttpStatus.OK);
		 
	}
	@RequestMapping(value = "/dashboard/user/{userid}",method = RequestMethod.GET)
	public Map<String, String> getUserDetail(@PathVariable("userid") Integer userid){
		Map<String, String> map=new HashMap<>();
		List<Task> list=taskService.getAllTask();
		Long totaltask=list.stream().filter(x->x.getUser_id().equals(userid)).count();
		Long totalcompleted=list.stream().filter(x->x.getUser_id().equals(userid)).filter(x->x.isIsActive()).count();
		List<TaskAssign> list2=taskAssignService.getAll();
		Long startworkingLong=list2.stream().filter(x->x.getUser().equals(userid)).filter(x->x.getTaskid()!=x.getTaskid()).count();
		Long totaldoneLong=list2.stream().filter(x->x.getUser_id().equals(userid)).filter(x->x.isCompleted()).filter(x->x.getTaskid()!=x.getTaskid()).count();
		Long pendingtaskLong=startworkingLong-totaldoneLong;
		List<User> listuList=userServiceImp.getUserList();
		
		map.put("totaluser", ""+listuList.size());
		map.put("totaltask", ""+totaltask);
		map.put("assign", ""+totalcompleted);
		map.put("start", ""+startworkingLong);
		map.put("completed", ""+totaldoneLong);
		map.put("pending", ""+pendingtaskLong);
		return map;
	}
	
	@RequestMapping(value =   "/taskupdate/{id}",method = RequestMethod.GET)
	public Map<String, String> update(@PathVariable("id") Long id){
		Map<String, String> map=new HashMap<>();
		map.put("message", "Task Status Update");
		Task task=taskService.getTask(id);
		task.setIsActive(true);
		taskService.save(task);
		
		return map;
	}
	
	
}
