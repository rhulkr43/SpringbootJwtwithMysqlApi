package com.project.entity;

import java.time.LocalDate;

public class Validation {
	private LocalDate date;
	  private String message;
	  private String details;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Validation(LocalDate date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
	public Validation() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}
