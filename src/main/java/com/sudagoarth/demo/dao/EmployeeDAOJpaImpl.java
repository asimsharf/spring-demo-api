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
    public void save(Employee theEmployee) {
        entityManager.persist(theEmployee);
    }

    @Override
    public Employee findById(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee order by lastName asc", Employee.class);
        System.out.println("findAll() called" + theQuery.getResultList());
        return theQuery.getResultList();
    }

    @Override
    public List<Employee> findByLastName(String theLastName) {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee where lastName=:theData", Employee.class);
        theQuery.setParameter("theData", theLastName);
        return theQuery.getResultList();
    }

    @Override
    public void update(Employee theEmployee) {
        entityManager.merge(theEmployee);
    }

    @Override
    public void delete(Integer theID) {
        Employee tempEmployee = entityManager.find(Employee.class, theID);
        entityManager.remove(tempEmployee);
    }

    @Override
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Employee").executeUpdate();
    }
}
