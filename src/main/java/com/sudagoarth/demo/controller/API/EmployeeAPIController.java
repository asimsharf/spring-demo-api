package com.sudagoarth.demo.controller.API;

import com.sudagoarth.demo.entity.Employee;
import com.sudagoarth.demo.response.TheResponse;
import com.sudagoarth.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeAPIController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeAPIController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/employees")
	public ResponseEntity<Object> findAll() {
		return TheResponse.getResponse("Request Employees List", HttpStatus.OK, employeeService.findAll(), 1);
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Object> findById(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return TheResponse.getResponse("Request Employee", HttpStatus.OK, theEmployee, 1);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> save(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return TheResponse.getResponse("Employee Added", HttpStatus.OK, theEmployee, 1);
	}

	@PutMapping("/employees")
	public ResponseEntity<Object> update(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return TheResponse.getResponse("Employee Updated", HttpStatus.OK, theEmployee, 1);
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Object> deleteById(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		employeeService.deleteById(employeeId);
		return TheResponse.getResponse("Employee Deleted", HttpStatus.OK, tempEmployee, 1);
	}

}




