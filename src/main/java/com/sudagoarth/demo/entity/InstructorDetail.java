package com.sudagoarth.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructor_detail")
public class InstructorDetail {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @NotNull(message="Youtube Channel is required")
        @Size(min=1, message="is required")
        @Column(name = "youtube_channel")
        private String youtubeChannel;

        @NotNull(message="Hobby Name is required")
        @Size(min=1, message="is required")
        @Column(name = "hobby")
        private String hobby;
        @JsonIgnore
        @OneToOne(mappedBy="instructorDetail",
                cascade={
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH
        })
        @JoinColumn(name = "instructor_id")
        private Instructor instructor;

}
