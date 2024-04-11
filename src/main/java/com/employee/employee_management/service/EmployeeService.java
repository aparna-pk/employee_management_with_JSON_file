package com.employee.employee_management.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.employee.employee_management.dao.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class EmployeeService {

	List<Employee> listEmployee = new ArrayList<Employee>();

	public String save(Employee emp) {
		autoGenerateId(emp);
		listEmployee.add(emp);
		try {
			saveToFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return "success";
	}

	private void autoGenerateId(Employee employee) {
		employee.setId(UUID.randomUUID());
	}

	public void saveToFile() throws IOException {

		FileWriter file = new FileWriter("Employee.json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(listEmployee);
			file.write(json);

			file.close();

	}

	public static void main(String[] args) {
		try {
			getEmp();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static List<Employee> getEmp() throws IOException, ParseException {

		// read from file

		List<Employee> employeeList = new ArrayList<Employee>();

		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("Employee.json");
		Object obj;
		
			obj = jsonParser.parse(reader);
			employeeList = (List<Employee>) obj;
		
		reader.close();

		return employeeList;
	}

	public boolean deleteEmployeeById(UUID id) throws IOException {

		for (Employee employee : listEmployee) {
			if (employee.getId().equals(id)) {
				listEmployee.remove(employee);

				saveToFile();
				return true;
			}
		}
		return false;
	}

	public Employee getEmployeeById(UUID id) {
		for (Employee emp : listEmployee) {
			if (emp.getId().equals(id)) {
				return emp;
			}
		}
		return null;
	}

	public void updateEmployee(Employee updatedEmployee) {

		// remove from list
		listEmployee
				.removeIf(employee -> employee.getId().toString().equalsIgnoreCase(updatedEmployee.getId().toString()));
		// add to employee
		listEmployee.add(updatedEmployee);

		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
