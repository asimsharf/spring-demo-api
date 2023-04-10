package com.sudagoarth.demo.service;

import com.sudagoarth.demo.entity.Employee;

import java.util.List;

public interface IEmployeeService {
	List<Employee> findAll();
	Employee findById(int theId);
	Employee save(Employee theEmployee);
	void deleteById(int theId);
}
