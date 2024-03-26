package com.hangahae.st.demo.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplyLectureRequest {

    @NotBlank
    private String userId;
}
