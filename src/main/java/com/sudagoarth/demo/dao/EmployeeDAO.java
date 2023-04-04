package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee theEmployee);

    void deleteById(Integer theID);

}
