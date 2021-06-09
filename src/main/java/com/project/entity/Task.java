package com.project.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long task_id;
	private String title;
	private String comment;
	private LocalDate assigndate;
	private boolean IsActive;
	private Integer user_id;
	
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getAssigndate() {
		return assigndate;
	}
	public void setAssigndate(LocalDate assigndate) {
		this.assigndate = assigndate;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Task(Long task_id, String title, String comment, LocalDate assigndate, boolean isActive, Integer user_id
			) {
		super();
		this.task_id = task_id;
		this.title = title;
		this.comment = comment;
		this.assigndate = assigndate;
		IsActive = isActive;
		this.user_id = user_id;
		
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getTask_id() {
		return task_id;
	}
	

}
