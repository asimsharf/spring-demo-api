package com.sudagoarth.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
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

        @NotNull(message="Hobby is required")
        @Size(min=1, message="is required")
        @Column(name = "hobby")
        private String hobby;
        @JsonIgnore
        @OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
        @JoinColumn(name = "instructor_id")
        private Instructor instructor;

        public Instructor getInstructor() {
            return instructor;
        }

        public void setInstructor(Instructor instructor) {
            this.instructor = instructor;
        }

        public InstructorDetail() {
        }

        public InstructorDetail(String youtubeChannel, String hobby) {
            this.youtubeChannel = youtubeChannel;
            this.hobby = hobby;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getYoutubeChannel() {
            return youtubeChannel;
        }

        public void setYoutubeChannel(String youtubeChannel) {
            this.youtubeChannel = youtubeChannel;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        @Override
        public String toString() {
            return "InstructorDetail{" + "id=" + id + ", youtubeChannel='" + youtubeChannel + '\'' + ", hobby='" + hobby + '\'' + '}';
        }
}
