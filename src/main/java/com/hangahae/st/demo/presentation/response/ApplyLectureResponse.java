package com.hangahae.st.demo.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplyLectureResponse {
    private String lectureId;
    private String userId;
    private Boolean applyResult;
}
