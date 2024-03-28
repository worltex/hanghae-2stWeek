package com.hangahae.st.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureRegistrationId;
    @Column(name = "userId")
    private String userId;

    @Column(name = "lectureId")
    private String lectureId;

    @Column(name = "registration_status")
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    public void updateRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }


    public Enrollment(String userId, String lectureId, RegistrationStatus registrationStatus) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.registrationStatus = registrationStatus;
    }
}
