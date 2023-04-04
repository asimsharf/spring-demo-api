package com.sudagoarth.demo.rest;


import com.sudagoarth.demo.entity.Student;
import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentsController {

    List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Mohamed", "Qadir","mohame@gmail.com"));
        theStudents.add(new Student("Ahmed", "Yahya","ahmed@gmail.com"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student theStudent) {
        theStudents.add(theStudent);
        return theStudent;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent) {
        theStudents.add(theStudent);
        return theStudent;
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        theStudents.remove(studentId);
        return "Deleted student id - " + studentId;
    }

}
