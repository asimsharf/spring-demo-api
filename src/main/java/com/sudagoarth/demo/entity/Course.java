package com.sudagoarth.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToOne(cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name="instructor_id")
    private Instructor instructor;


    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews;

    @ManyToMany(cascade= {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(
                    name = "course_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id"
            )
    )
    private List<Student> students;


    public void addReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(theReview);
    }

    public void removeReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.remove(theReview);
    }

    public void addStudent(Student theStudent){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(theStudent);
    }

    public void removeStudent(Student theStudent){
        if(students == null){
            students = new ArrayList<>();
        }
        students.remove(theStudent);
    }

}
