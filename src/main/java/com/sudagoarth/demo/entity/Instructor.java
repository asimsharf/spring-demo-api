package com.sudagoarth.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="instructor", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

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

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", instructorDetail=" + instructorDetail + '}';
    }
}
