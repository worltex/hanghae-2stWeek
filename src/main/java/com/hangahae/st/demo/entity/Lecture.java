package com.hangahae.st.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @Column(name = "lectureId")
    private String id;

    @Column(name = "lecture_date")
    private ZonedDateTime lectureDate;

    @Column(name = "max_enrollment")
    private int maxEnrollment;

    @Column(name = "current_enrollment")
    private int currentEnrollment;


    public void updateCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }
}
