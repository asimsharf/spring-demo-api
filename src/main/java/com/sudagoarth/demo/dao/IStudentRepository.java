package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findAllByOrderByLastNameAsc();
	
}
