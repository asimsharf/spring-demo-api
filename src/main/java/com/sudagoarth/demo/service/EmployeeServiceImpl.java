package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.EmployeeRepository;
import com.sudagoarth.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
		return theEmployee;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}






