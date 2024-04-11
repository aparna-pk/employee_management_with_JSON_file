package com.employee.employee_management.dao;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employee implements Serializable {

	static final long serialVersionID = 1l;
	private UUID id;

	@NotBlank(message = "must enter name")
	@Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
	@Pattern(regexp = "\\D+", message = "Field must not contain numbers")
	private String name;

	@NotBlank(message = "invalid phone number")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
	private String phoneNumber;

	@NotBlank(message = "invalid age")
	@Max(value = 80, message = "age should be less than 80")
	@Min(value = 18)
	private String age;

	@NotBlank(message = "invalid department")
	@Pattern(regexp = "\\D+", message = "Field must not contain numbers")
	private String department;

	public Employee() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", age=" + age
				+ ", department=" + department + "]";
	}

}
