package com.sudagoarth.demo.controller;


import com.sudagoarth.demo.entity.Course;
import com.sudagoarth.demo.service.ICourseServices;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    ICourseServices icourseServices;

    public CourseController(ICourseServices theICourseService) {
        icourseServices = theICourseService;
    }

    @GetMapping("/list")
    public String list(Model theModel) {
        List<Course> theCourses = icourseServices.findAll();
        theModel.addAttribute("courses", theCourses);
        return "courses/list-courses";
    }

    @RequestMapping("/showFormForAdd")
    public String create(Model theModel) {
        Course theCourse = new Course();
        theModel.addAttribute("course", theCourse);

        return "courses/course-form";

    }

    @RequestMapping("/showFormForUpdate")
    public String update(@RequestParam("courseId") int theId, Model theModel) {
        Course theCourse = icourseServices.findById(theId);
        theModel.addAttribute("course", theCourse);
        return "courses/course-form";
    }

    @RequestMapping("/save")
    public String save(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "courses/course-form";
        } else {
            icourseServices.save(theCourse);
            return "redirect:/courses/list";
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("courseId") int theId) {
        icourseServices.deleteById(theId);
        return "redirect:/courses/list";
    }

}
