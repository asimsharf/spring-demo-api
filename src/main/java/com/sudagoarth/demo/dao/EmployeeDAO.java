package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    void save(Employee theEmployee);

    Employee findById(Integer id);

    List<Employee> findAll();

    List<Employee> findByLastName (String theLastName);

    void update(Employee theEmployee);

    void delete(Integer theID);

    int deleteAll();



}
