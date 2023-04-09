package com.sudagoarth.demo.controller;


import com.sudagoarth.demo.entity.Course;
import com.sudagoarth.demo.service.CourseServices;
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

    CourseServices courseService;

    public CourseController(CourseServices theCourseService) {
        courseService = theCourseService;
    }

    @GetMapping("/list")
    public String list(Model theModel) {
        List<Course> theCourses = courseService.findAll();
        theModel.addAttribute("courses", theCourses);
        return "courses/list-courses";
    }

    @RequestMapping("/showFormForAdd")
    public String show(Model theModel) {
        Course theCourse = new Course();
        theModel.addAttribute("course", theCourse);

        return "courses/course-form";

    }

    @RequestMapping("/showFormForUpdate")
    public String update(@RequestParam("courseId") int theId, Model theModel) {
        Course theCourse = courseService.findById(theId);
        theModel.addAttribute("course", theCourse);
        return "courses/course-form";
    }

    @RequestMapping("/save")
    public String save(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "courses/course-form";
        } else {
            courseService.save(theCourse);
            return "redirect:/courses/list";
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("courseId") int theId) {
        courseService.deleteById(theId);
        return "redirect:/courses/list";
    }

}
