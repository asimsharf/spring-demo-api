package com.sudagoarth.demo.controller;

import com.sudagoarth.demo.entity.Instructor;
import com.sudagoarth.demo.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    InstructorService instructorService;

    public InstructorController(InstructorService theEmployeeService) {
        instructorService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Instructor> theInstructors = instructorService.findAll();
        theModel.addAttribute("instructors", theInstructors);
        return "instructors/list-instructors";
    }

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Instructor theInstructor = new Instructor();
        theModel.addAttribute("instructor", theInstructor);

        return "instructors/instructor-form";

    }

    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("instructorId") int theId, Model theModel) {
        Instructor theInstructor = instructorService.findById(theId);
        theModel.addAttribute("instructor", theInstructor);
        return "instructors/instructor-form";
    }

    @RequestMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("instructor") Instructor theInstructor, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "instructors/instructor-form";
        } else {
            instructorService.save(theInstructor);
            return "redirect:/instructors/list";
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("instructorId") int theId) {
        instructorService.deleteById(theId);
        return "redirect:/instructors/list";
    }

    
}
