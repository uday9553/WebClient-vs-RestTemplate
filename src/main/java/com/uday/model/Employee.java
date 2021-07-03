package com.uday.model;

public class Employee {
	
	private String name, role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Employee(String name, String role) {		
		this.name = name;
		this.role = role;
	}

	public Employee() {
		
	}
	

}
