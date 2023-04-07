package com.sudagoarth.demo.controller;

import com.sudagoarth.demo.entity.Employee;
import com.sudagoarth.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	/*
	 * This method will be called for every web request coming into our controller.
	 * It will trim the input strings and remove leading and trailing whitespace.
	 * If the string only contains whitespace, it will be converted to null.
	 * This will resolve our issue for our validation.
	 * @InitBinder annotation is used to tell Spring MVC to call this method for every
	 * web request coming into our controller.
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees = employeeService.findAll();
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		Employee theEmployee = employeeService.findById(theId);
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}


	/*
	 * saveEmployee() method is called when the user clicks the submit button on the
	 * form. It will save the employee to the database.
	 * @Valid annotation is used to validate the employee object. BindingResult
	 * object will hold the result of the validation.
	 * If there are any errors, we will return the user to the form page.
	 * If there are no errors, we will save the employee to the database and redirect
	 * the user to the list-employees page.
	 */
	@RequestMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee, BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "employees/employee-form";
		} else {
			employeeService.save(theEmployee);
			return "redirect:/employees/list";
		}
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}

}









