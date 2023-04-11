package com.sudagoarth.demo.controller.API;

import com.sudagoarth.demo.entity.Employee;
import com.sudagoarth.demo.response.TheResponse;
import com.sudagoarth.demo.service.IEmployeeService;
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

	private final IEmployeeService IEmployeeService;

	public EmployeeAPIController(IEmployeeService theIEmployeeService) {
		IEmployeeService = theIEmployeeService;
	}

	@GetMapping("/employees")
	public ResponseEntity<Object> findAll() {
		return TheResponse.getResponse("Request Employees List", HttpStatus.OK, IEmployeeService.findAll(), 1);
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Object> findById(@PathVariable int employeeId) {
		Employee theEmployee = IEmployeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return TheResponse.getResponse("Request Employee", HttpStatus.OK, theEmployee, 1);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> save(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		IEmployeeService.save(theEmployee);
		return TheResponse.getResponse("Employee Added", HttpStatus.OK, theEmployee, 1);
	}

	@PutMapping("/employees")
	public ResponseEntity<Object> update(@RequestBody Employee theEmployee) {
		IEmployeeService.save(theEmployee);
		return TheResponse.getResponse("Employee Updated", HttpStatus.OK, theEmployee, 1);
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Object> deleteById(@PathVariable int employeeId) {
		Employee tempEmployee = IEmployeeService.findById(employeeId);
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		IEmployeeService.deleteById(employeeId);
		return TheResponse.getResponse("Employee Deleted", HttpStatus.OK, tempEmployee, 1);
	}

}




