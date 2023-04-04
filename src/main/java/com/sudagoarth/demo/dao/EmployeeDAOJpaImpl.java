package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private final EntityManager entityManager;

    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee order by lastName asc", Employee.class);
        System.out.println("findAll() called" + theQuery.getResultList());
        return theQuery.getResultList();
    }

    @Override
    public Employee save(Employee theEmployee) {
        return entityManager.merge(theEmployee);
    }

    @Override
    public Employee findById(Integer id) {
        return entityManager.find(Employee.class, id);
    }



    @Override
    public void deleteById(Integer theID) {
        Employee tempEmployee = entityManager.find(Employee.class, theID);
        entityManager.remove(tempEmployee);
    }

}
