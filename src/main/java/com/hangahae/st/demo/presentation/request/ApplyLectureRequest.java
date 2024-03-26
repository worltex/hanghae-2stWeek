package com.hangahae.st.demo.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ApplyLectureRequest {

    @NotBlank
    private String userId;
}
