package com.aixtor.training.liferay.model;

public class Employee {

	private String name;
	private String email;
	private String country;
	private String state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", email=" + email + ", country=" + country + ", state=" + state + "]";
	}

	public Employee(String name, String email, String country, String state) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.state = state;
	}

}
