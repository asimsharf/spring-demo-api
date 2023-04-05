package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(path = "students")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // that's it ... no need to write any code LOL!

}
