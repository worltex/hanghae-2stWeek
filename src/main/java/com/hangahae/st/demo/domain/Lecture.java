package com.hangahae.st.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Lecture {
    @Id
    @Column(name = "lectureId")
    private long id;

    @Column(name ="lecture_date")
    private ZonedDateTime lectureDate;

    @Column(name ="max_enrollemtn")
    private int maxEnrollment;
}
