package com.sudagoarth.demo.dao;

import com.sudagoarth.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
