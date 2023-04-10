package com.sudagoarth.demo.controller.API;

import com.sudagoarth.demo.entity.Course;
import com.sudagoarth.demo.entity.Instructor;
import com.sudagoarth.demo.response.TheResponse;
import com.sudagoarth.demo.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InstructorAPIController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private final IInstructorService IInstructorService;

    @Autowired
    public InstructorAPIController(IInstructorService theIInstructorService) {
        IInstructorService = theIInstructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<Object> findAll() {
        return  TheResponse.getResponse("Request All Instructors", HttpStatus.OK, IInstructorService.findAll(), 1);
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<Object> findById(@PathVariable int instructorId) {
        Instructor theInstructor = IInstructorService.findById(instructorId);
        if (theInstructor == null) {
            return TheResponse.getResponse("Instructor with ID - " + instructorId + " Not Found", HttpStatus.NOT_FOUND, null, 0);
        }
        return TheResponse.getResponse("Request Instructor", HttpStatus.OK, theInstructor, 1);
    }

    @PostMapping("/instructors")
    public ResponseEntity<Object> save(@RequestBody Instructor theInstructor) {
        IInstructorService.save(theInstructor);
        return TheResponse.getResponse("Request Instructor", HttpStatus.OK, theInstructor, 1);
    }

    @PutMapping("/instructors")
    public ResponseEntity<Object> update(@RequestBody Instructor theInstructor) {
        Instructor tempInstructor = IInstructorService.findById(theInstructor.getId());
        if(tempInstructor == null){
            return TheResponse.getResponse("Instructor with ID - " + theInstructor.getId() + " Not Found", HttpStatus.NOT_FOUND, null, 0);
        }
        IInstructorService.save(theInstructor);
        return TheResponse.getResponse("Instructor Updated", HttpStatus.OK, theInstructor, 1);
    }

    @DeleteMapping("/instructors/{instructorId}")
    public ResponseEntity<Object> deleteById(@PathVariable int instructorId) {
        Instructor tempInstructor = IInstructorService.findById(instructorId);
        if (tempInstructor == null) {
            return TheResponse.getResponse("Instructor with ID - " + instructorId + " Not Found", HttpStatus.NOT_FOUND, null, 0);
        }
        IInstructorService.deleteById(instructorId);
        return TheResponse.getResponse("Instructor with ID - " + instructorId + " Deleted successfully", HttpStatus.OK, null, 1);
    }


    @PostMapping("/instructors/{instructorId}/add-course")
    public ResponseEntity<Object> addCourses(@PathVariable int instructorId, @RequestBody Course theCourse) {
        Instructor theInstructor = IInstructorService.findById(instructorId);
        if (theInstructor == null) {
            return TheResponse.getResponse("Instructor with ID - " + instructorId + " Not Found", HttpStatus.NOT_FOUND, null, 0);
        }
        theInstructor.addCourse(theCourse);
        IInstructorService.save(theInstructor);
        return TheResponse.getResponse("Request Add Course To Instructor", HttpStatus.OK, theInstructor, 1);
    }

    @PostMapping("/instructors/{instructorId}/remove-course")
    public ResponseEntity<Object> removeCourse(@PathVariable int instructorId, @RequestBody Course theCourse) {
        Instructor theInstructor = IInstructorService.findById(instructorId);
        if (theInstructor == null) {
            return TheResponse.getResponse("Instructor with ID - " + instructorId + " Not Found", HttpStatus.NOT_FOUND, null, 0);
        }
        theInstructor.removeCourse(theCourse);
        IInstructorService.save(theInstructor);
        return TheResponse.getResponse("Request Remove Course From Instructor", HttpStatus.OK, theInstructor, 1);
    }

}
