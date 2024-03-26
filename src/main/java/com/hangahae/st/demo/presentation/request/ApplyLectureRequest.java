package com.hangahae.st.demo.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLectureRequest {

    @NotBlank
    private String userId;
}
