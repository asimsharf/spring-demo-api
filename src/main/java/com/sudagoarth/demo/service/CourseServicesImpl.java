package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.CourseRepository;
import com.sudagoarth.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServicesImpl implements CourseServices{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServicesImpl(CourseRepository theCourseRepository){
        courseRepository = theCourseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);
        Course theCourse = null;
        if (result.isPresent()) {
            theCourse = result.get();
        }
        return theCourse;
    }

    @Override
    public Course save(Course theCourse) {
        return courseRepository.save(theCourse);
    }

    @Override
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }
}
