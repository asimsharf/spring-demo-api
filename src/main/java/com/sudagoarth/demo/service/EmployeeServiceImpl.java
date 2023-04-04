package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.EmployeeDAO;
import com.sudagoarth.demo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return  employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(Integer theID) {
        employeeDAO.deleteById(theID);
    }

}
