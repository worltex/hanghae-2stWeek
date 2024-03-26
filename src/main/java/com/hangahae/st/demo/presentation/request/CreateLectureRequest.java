package com.hangahae.st.demo.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLectureRequest {

    private int maxEnrollment;
    private int currentEnrollment;
}
