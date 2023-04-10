package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Integer> {

}
