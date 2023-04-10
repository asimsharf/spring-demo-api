package com.sudagoarth.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message="First Name is required")
    @Size(min=1, message="is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message="Last Name is required")
    @Size(min=1, message="is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Email is required")
    @Size(min=1, message="is required")
    @Email(message="Email is not a valid email address")
    @Column(name = "email")
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="instructor", cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Course> courses;


    public void addCourse(Course theCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(theCourse);
        theCourse.setInstructor(this);
    }

    public void removeCourse(Course theCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.remove(theCourse);
        theCourse.setInstructor(null);
    }

}
