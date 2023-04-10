package com.sudagoarth.demo.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    @Size(min = 3, max = 10, message = "Invalid first name!(3-10 char)")
    private String firstName;

    @Size(min = 3, max = 10, message = "Invalid last name!(3-10 char)")
    private String lastName;

    @Size(min = 3, max = 10, message = "Invalid username!(3-10 char)")
    private String username;

    @Size(min = 6, max = 10, message = "Invalid password!(6-10 char)")
    private String password;

    private String repeatPassword;
}
