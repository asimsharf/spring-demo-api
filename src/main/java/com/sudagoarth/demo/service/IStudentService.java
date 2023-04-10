package com.sudagoarth.demo.service;

import com.sudagoarth.demo.entity.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();
    Student findById(int theId);
    Student save(Student theEmployee);
    void deleteById(int theId);
    
}
