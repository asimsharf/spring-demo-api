package com.sudagoarth.demo.rest;

import com.sudagoarth.demo.dao.StudentDAO;
import com.sudagoarth.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentDAO studentDAO;

    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id) {
        return studentDAO.findById(id);
    }

}
