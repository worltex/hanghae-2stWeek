package com.hangahae.st.demo.presentation;

import com.hangahae.st.demo.domain.Lecture;
import com.hangahae.st.demo.domain.RegistrationStatus;
import com.hangahae.st.demo.dto.LectureDto;
import com.hangahae.st.demo.presentation.request.ApplyLectureRequest;
import com.hangahae.st.demo.presentation.request.CreateLectureRequest;
import com.hangahae.st.demo.presentation.request.SearchLectureRequest;
import com.hangahae.st.demo.presentation.response.ApplyLectureResponse;
import com.hangahae.st.demo.serive.EnrollmentService;
import com.hangahae.st.demo.serive.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final EnrollmentService enrollmentService;
    private final LectureService lectureService;

    @PostMapping
    public Lecture createLecture(@RequestBody CreateLectureRequest request) {
        return lectureService.createLecture(request.getMaxEnrollment(), request.getCurrentEnrollment());
    }

    @PostMapping("/{lectureId}")
    public void applyLecture(@PathVariable String lectureId, @RequestBody ApplyLectureRequest request) {
        //1. Lecture 조회 (total_enrollment, current_enrollment)
        //1.2 current_enrollment>total_enrollment 넘는 경우 에러 처리
        String userId = request.getUserId();
        Lecture lecture = lectureService.getLectureDtoByLectureId(lectureId);
        if (lecture.getMaxEnrollment() <= lecture.getCurrentEnrollment()) {
            throw new RuntimeException("정원 초과되었습니다.");
        }
        //2. 동일한 신청자의 중복 요청이 있는지 확인
        //2.1 동일한 요청이 있는 경우 에러 처리
        //2.2 없는 경우 Lecture Registration 테이블에 status REGISTERING
        enrollmentService.saveEnrollmentStatus(lecture, request.getUserId(), RegistrationStatus.REGISTERING);

        //3. current_enrollemnt+1 > total_enrollment인지 확인
        //3.1 큰 경우 RESTRATION STATUS row FAILED 처리 => 재시도 처리
        //3.2 작은 경우 COMPLETED 처리 및 current_enrollment +1 처리
        try {
            lectureService.updateLectureEnrollment(lectureId);
        } catch (Exception e) {
            enrollmentService.updateEnrollmentStatus(lectureId, userId, RegistrationStatus.FAILED);
            throw e;
        }
        enrollmentService.updateEnrollmentStatus(lectureId, userId, RegistrationStatus.REGISTERED);
    }

    @GetMapping("/{lectureId}")
    public ApplyLectureResponse getLectureRegistrationResult(@PathVariable String lectureId, @RequestParam String userId) {
        boolean registerResult = enrollmentService.existEnrollmentByLectureIdAndUserId(lectureId, userId);
        return new ApplyLectureResponse(lectureId, userId, registerResult);
    }

    @GetMapping
    public List<LectureDto> getLectureList(SearchLectureRequest request) {
        return lectureService.getLectureList(request.getLectureDate());
    }

}
