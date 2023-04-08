package com.sudagoarth.demo.service;

import com.sudagoarth.demo.entity.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> findAll();

    Instructor findById(int theId);

    void save(Instructor theInstructor);

    void deleteById(int theId);

}
