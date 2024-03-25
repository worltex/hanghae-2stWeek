package com.hangahae.st.demo.presentation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ApplyLectureRequest {

    @NotBlank
    private String userId;
}
