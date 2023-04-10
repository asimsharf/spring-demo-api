package com.sudagoarth.demo.controller;

import com.sudagoarth.demo.entity.Student;
import com.sudagoarth.demo.service.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	IStudentService IStudentService;

	public StudentController(IStudentService theIStudentService) {
		IStudentService = theIStudentService;
	}

	@GetMapping("/list")
	public String list(Model theModel) {
		List<Student> theStudents = IStudentService.findAll();
		theModel.addAttribute("students", theStudents);
		return "students/list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String create(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);

		return "students/student-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String update(@RequestParam("studentId") int theId, Model theModel) {
		Student theStudent = IStudentService.findById(theId);
		theModel.addAttribute("student", theStudent);
		return "students/student-form";
	}

	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("student") Student theStudent, BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "students/student-form";
		} else {
			IStudentService.save(theStudent);
			return "redirect:/students/list";
		}
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		IStudentService.deleteById(theId);
		return "redirect:/students/list";
	}

}









