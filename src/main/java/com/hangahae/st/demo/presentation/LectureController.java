package com.hangahae.st.demo.presentation;

import com.hangahae.st.demo.serive.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture/{lectureId}")
public class LectureController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public void applyLecture(@RequestBody ApplyLectureRequest request) {
        //1. Lecture 조회 (total_enrollment, current_enrollment) LOCK 30초 정도
        //1.2 current_enrollment>total_enrollment 넘는 경우 에러 처리


        //2. 동일한 신청자의 중복 요청이 있는지 확인
        //2.1 동일한 요청이 있는 경우 에러 처리
        //2.2 없는 경우 Lecture Registration 테이블에 status REGISTERING


        //3. current_enrollemnt+1 > total_enrollment인지 확인
        //3.1 큰 경우 Lecture Registration row 삭제 처리 => 에러 처리
        //3.2 작은 경우 COMPLETED 처리 및 current_enrollment +1 처리
    }

    @GetMapping
    public ApplyLectureResponse getLectureRegistrationResult(@PathVariable String lectureId, @RequestParam String userId) {
        boolean registerResult = enrollmentService.existEnrollmentByLectureIdAndUserId(lectureId, userId);
        return new ApplyLectureResponse(lectureId, userId, registerResult);
    }

}
