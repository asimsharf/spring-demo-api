package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository  extends JpaRepository<Instructor, Integer> {

}
