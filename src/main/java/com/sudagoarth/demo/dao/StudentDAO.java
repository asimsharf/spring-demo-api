package com.sudagoarth.demo.dao;


import com.sudagoarth.demo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(Integer id);

     List<Student> findAll();

    List<Student> findByLastName (String theLastName);

    void update(Student theStudent);

    void delete(Integer theID);

    int deleteAll();
}