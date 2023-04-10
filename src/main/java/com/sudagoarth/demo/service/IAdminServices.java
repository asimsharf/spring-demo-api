package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dto.AdminDto;
import com.sudagoarth.demo.entity.Admin;

public interface IAdminServices {

    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);

}
