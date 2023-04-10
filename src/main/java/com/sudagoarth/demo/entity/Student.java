package com.sudagoarth.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @NotNull(message = "First Name is required")
    @Size(min=1, message="is required")
    @Column(name = "first_name")
    String firstName;

    @NotNull(message = "Last Name is required")
    @Size(min=1, message="is required")
    @Column(name = "last_name")
    String lastName;

    @NotNull(message="Email is required")
    @Size(min=1, message="is required")
    @Email(message="is not a valid email address")
    @Column(name = "email")
    String email;

    @ManyToMany(cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(
                    name = "student_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id"
            )
    )
    private List<Course> courses;

}