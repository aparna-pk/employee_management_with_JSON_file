package com.employee.employee_management.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.employee.employee_management.dao.Employee;
import com.employee.employee_management.service.EmployeeService;

import jakarta.validation.Valid;

@Controller

public class AddEmployeeController  {

	@Autowired
	EmployeeService empsv;
	private static final Logger logger = LoggerFactory.getLogger(AddEmployeeController.class);
	String viewurl="redirect:/viewEmployee";
	String addurl="AddEmployee";

	

	@RequestMapping("/")
	public String viewHomePage() {
		return "home";
	}

	@RequestMapping("/addEmployee")
	public String addEmployeePage(Employee employee) {
		return addurl ;

	}

	@PostMapping("/add")
	public String addEmployee(@Valid Employee employee, BindingResult result) {

		if (result.hasErrors()) {
			logger.warn("Validation errors: {}", result);
			return addurl;
		}

		empsv.save(employee);

		return viewurl;
	}

	@RequestMapping("/viewEmployee")
	public String viewEmployee(Model model) {
		List<Employee> employeelist = null;
		try {
			employeelist = empsv.getEmp();
			model.addAttribute("allemplist", employeelist);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return "ViewEmployee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") UUID id) {

		try {
			empsv.deleteEmployeeById(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return viewurl;
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") UUID id, Model model) {

		Employee employee = empsv.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@Valid @ModelAttribute("employee") Employee updatedEmployee, BindingResult result) {

		if (result.hasErrors()) {
			logger.warn("Validation errors: {}", result);
			return addurl;
		}

		empsv.updateEmployee(updatedEmployee);
		return viewurl;
	}

}
