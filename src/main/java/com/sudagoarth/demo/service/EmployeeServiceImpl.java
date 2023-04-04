package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.EmployeeRepository;
import com.sudagoarth.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        this.employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return  employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(Integer theID) {
        employeeRepository.deleteById(theID);
    }

}
