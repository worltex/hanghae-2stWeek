package com.hangahae.st.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnrollmentId implements Serializable {

    @Column(name = "userId")
    private String userId;

    @Column(name = "lectureId")
    private String lectureId;
}
