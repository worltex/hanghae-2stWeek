package com.hangahae.st.demo.domain;

import jakarta.persistence.*;

@Entity
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;


    @Column(name="registration_status")
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;
}
