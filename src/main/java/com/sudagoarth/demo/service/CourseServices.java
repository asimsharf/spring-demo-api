package com.sudagoarth.demo.service;

import com.sudagoarth.demo.entity.Course;

import java.util.List;

public interface CourseServices {
    List<Course> findAll();
    Course findById(int theId);
    void save(Course theCourse);
    void deleteById(int theId);
}
