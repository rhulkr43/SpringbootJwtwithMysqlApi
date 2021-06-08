package com.project.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskAssign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long task_assign_id;
	
	private String usercomment;
	private LocalDate date;
	private Integer user_id;
	public Long getTask_assign_id() {
		return task_assign_id;
	}
	public void setTask_assign_id(Long task_assign_id) {
		this.task_assign_id = task_assign_id;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public TaskAssign(Long task_assign_id, String usercomment, LocalDate date, Integer user_id) {
		super();
		this.task_assign_id = task_assign_id;
		this.usercomment = usercomment;
		this.date = date;
		this.user_id = user_id;
	}
	public TaskAssign() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
