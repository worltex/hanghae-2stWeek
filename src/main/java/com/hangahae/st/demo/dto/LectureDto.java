package com.hangahae.st.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class LectureDto {

    private ZonedDateTime lectureDate;
    private int maxEnrollment;
    private int currentEnrollment;
}
