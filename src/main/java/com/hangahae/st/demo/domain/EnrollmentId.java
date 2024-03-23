package com.hangahae.st.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable {

    @Column(name = "userId")
    private String userId;

    @Column(name = "lectureId")
    private String lectureId;
}
