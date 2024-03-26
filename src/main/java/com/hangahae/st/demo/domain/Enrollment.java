package com.hangahae.st.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("lectureId")
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;


    @Column(name = "registration_status")
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    public void updateRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
