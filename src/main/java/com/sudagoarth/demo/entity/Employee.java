package com.sudagoarth.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="first_name")
	private String firstName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="last_name")
	private String lastName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Email(message="is not a valid email address")
	@Column(name="email")
	private String email;


}











