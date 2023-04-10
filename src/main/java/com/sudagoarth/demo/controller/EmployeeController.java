package com.sudagoarth.demo.controller;

import com.sudagoarth.demo.entity.Employee;
import com.sudagoarth.demo.service.IEmployeeService;
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

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	IEmployeeService IEmployeeService;

	public EmployeeController(IEmployeeService theIEmployeeService) {
		IEmployeeService = theIEmployeeService;
	}

	@GetMapping("/list")
	public String list(Model theModel) {
		List<Employee> theEmployees = IEmployeeService.findAll();
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";
	}

	@RequestMapping("/showFormForAdd")
	public String create(Model theModel) {
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String update(@RequestParam("employeeId") int theId, Model theModel) {
		Employee theEmployee = IEmployeeService.findById(theId);
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("employee") Employee theEmployee, BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "employees/employee-form";
		} else {
			IEmployeeService.save(theEmployee);
			return "redirect:/employees/list";
		}
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		IEmployeeService.deleteById(theId);
		return "redirect:/employees/list";
	}

}









