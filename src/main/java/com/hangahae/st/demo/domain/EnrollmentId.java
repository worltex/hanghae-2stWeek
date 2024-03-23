package com.hangahae.st.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable {

    @Column(name="userId")
    private long userId;

    @Column(name="lectureId")
    private long lectureId;
}
