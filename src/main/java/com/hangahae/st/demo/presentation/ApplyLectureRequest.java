package com.hangahae.st.demo.presentation;

import jakarta.validation.constraints.NotBlank;

public class ApplyLectureRequest {

    @NotBlank
    private long userId;

    @NotBlank
    private long lectureId;
}
