package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.IAdminRepository;
import com.sudagoarth.demo.dao.IRoleRepository;
import com.sudagoarth.demo.dto.AdminDto;
import com.sudagoarth.demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminServiceImpl implements IAdminServices {

    @Autowired
    private final IAdminRepository iadminRepository;

    @Autowired
    private final IRoleRepository iroleRepository;

    public AdminServiceImpl(IAdminRepository theIadminRepository, IRoleRepository theIroleRepository) {
        this.iadminRepository = theIadminRepository;
        this.iroleRepository = theIroleRepository;
    }


    @Override
    public Admin findByUsername(String username) {
        return iadminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());

        admin.setRoles(Collections.singletonList(iroleRepository.findByName("ADMIN")));

        return iadminRepository.save(admin);
    }
}
