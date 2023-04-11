package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.ICourseRepository;
import com.sudagoarth.demo.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServicesImpl implements ICourseServices {

    private final ICourseRepository icourseRepository;

    public CourseServicesImpl(ICourseRepository theICourseRepository){
        icourseRepository = theICourseRepository;
    }

    @Override
    public List<Course> findAll() {
        return icourseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = icourseRepository.findById(theId);
        Course theCourse = null;
        if (result.isPresent()) {
            theCourse = result.get();
        }
        return theCourse;
    }

    @Override
    public Course save(Course theCourse) {
        return icourseRepository.save(theCourse);
    }

    @Override
    public void deleteById(int theId) {
        icourseRepository.deleteById(theId);
    }

}
