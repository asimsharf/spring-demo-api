package com.sudagoarth.demo.controller.API;

import com.sudagoarth.demo.entity.Course;
import com.sudagoarth.demo.response.TheResponse;
import com.sudagoarth.demo.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CourseAPIController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private final CourseServices courseServices;

    @Autowired
    public CourseAPIController(CourseServices theCourseService){
        courseServices = theCourseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> findAll() {
        return TheResponse.getResponse("Request Course List", HttpStatus.OK, courseServices.findAll(), 1);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Object> findById(@PathVariable int courseId) {
        Course theEmployee = courseServices.findById(courseId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + courseId);
        }
        return TheResponse.getResponse("Request Course", HttpStatus.OK, theEmployee, 1);
    }

    @PostMapping("/courses")
    public ResponseEntity<Object> save(@RequestBody Course theCourse) {
        theCourse.setId(0);
        courseServices.save(theCourse);
        return TheResponse.getResponse("Course Added", HttpStatus.OK, theCourse, 1);
    }

    @PutMapping("/courses")
    public ResponseEntity<Object> update(@RequestBody Course theCourse) {
        courseServices.save(theCourse);
        return TheResponse.getResponse("Course Updated", HttpStatus.OK, theCourse, 1);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Object> deleteById(@PathVariable int courseId) {
        Course tempCourse = courseServices.findById(courseId);
        if (tempCourse == null) {
            throw new RuntimeException("Course id not found - " + courseId);
        }
        courseServices.deleteById(courseId);
        return TheResponse.getResponse("Course Deleted", HttpStatus.OK, tempCourse, 1);
    }

}
