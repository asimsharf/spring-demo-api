package com.sudagoarth.demo.controller.API;

import com.sudagoarth.demo.entity.Instructor;
import com.sudagoarth.demo.response.TheResponse;
import com.sudagoarth.demo.service.InstructorService;
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

    private final InstructorService instructorService;

    @Autowired
    public InstructorAPIController(InstructorService theInstructorService) {
        instructorService = theInstructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<Object> findAll() {
        return TheResponse.getResponse("Request Instructors List", HttpStatus.OK, instructorService.findAll(), 1);
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<Object> findById(@PathVariable int instructorId) {
        Instructor theInstructor = instructorService.findById(instructorId);
        if (theInstructor == null) {
            throw new RuntimeException("Instructor id not found - " + instructorId);
        }
        return TheResponse.getResponse("Request Instructor", HttpStatus.OK, theInstructor, 1);
    }

    @PostMapping("/instructors")
    public ResponseEntity<Object> save(@RequestBody Instructor theInstructor) {
        theInstructor.setId(0);
        instructorService.save(theInstructor);
        return TheResponse.getResponse("Instructor Added", HttpStatus.OK, theInstructor, 1);
    }

    @PutMapping("/instructors")
    public ResponseEntity<Object> update(@RequestBody Instructor theInstructor) {
        instructorService.save(theInstructor);
        return TheResponse.getResponse("Instructor Updated", HttpStatus.OK, theInstructor, 1);
    }

    @DeleteMapping("/instructors/{instructorId}")
    public ResponseEntity<Object> deleteById(@PathVariable int instructorId) {
        Instructor tempInstructor = instructorService.findById(instructorId);
        if (tempInstructor == null) {
            throw new RuntimeException("Instructor id not found - " + instructorId);
        }
        instructorService.deleteById(instructorId);
        return TheResponse.getResponse("Instructor Deleted", HttpStatus.OK, tempInstructor, 1);
    }
}
