package com.sudagoarth.demo.controller;

import com.sudagoarth.demo.entity.Instructor;
import com.sudagoarth.demo.service.IInstructorService;
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

    IInstructorService IInstructorService;

    public InstructorController(IInstructorService theEmployeeService) {
        IInstructorService = theEmployeeService;
    }

    @GetMapping("/list")
    public String list(Model theModel) {
        List<Instructor> theInstructors = IInstructorService.findAll();
        theModel.addAttribute("instructors", theInstructors);
        return "instructors/list-instructors";
    }

    @RequestMapping("/showFormForAdd")
    public String create(Model theModel) {
        Instructor theInstructor = new Instructor();
        theModel.addAttribute("instructor", theInstructor);
        return "instructors/instructor-form";

    }

    @RequestMapping("/showFormForUpdate")
    public String update(@RequestParam("instructorId") int theId, Model theModel) {
        Instructor theInstructor = IInstructorService.findById(theId);
        theModel.addAttribute("instructor", theInstructor);
        return "instructors/instructor-form";
    }

    @RequestMapping("/save")
    public String save(@Valid @ModelAttribute("instructor") Instructor theInstructor, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "instructors/instructor-form";
        } else {
            IInstructorService.save(theInstructor);
            return "redirect:/instructors/list";
        }
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam("instructorId") int theId) {
        IInstructorService.deleteById(theId);
        return "redirect:/instructors/list";
    }

    
}
