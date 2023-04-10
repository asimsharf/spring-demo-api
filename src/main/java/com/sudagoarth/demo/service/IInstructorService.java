package com.sudagoarth.demo.service;

import com.sudagoarth.demo.entity.Instructor;

import java.util.List;

public interface IInstructorService {

    List<Instructor> findAll();

    Instructor findById(int theId);

    Instructor save(Instructor theInstructor);

    void deleteById(int theId);

}
