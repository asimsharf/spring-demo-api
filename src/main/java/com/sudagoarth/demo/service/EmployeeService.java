package com.sudagoarth.demo.service;
import com.sudagoarth.demo.entity.Employee;

import java.util.List;

public interface EmployeeService  {


    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee theEmployee);

    void deleteById(Integer theID);

}
