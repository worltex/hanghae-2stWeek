package com.hangahae.st.demo.controller;

import com.hangahae.st.demo.controller.request.ApplyLectureRequest;
import com.hangahae.st.demo.controller.request.CreateLectureRequest;
import com.hangahae.st.demo.controller.request.SearchLectureRequest;
import com.hangahae.st.demo.controller.response.ApplyLectureResponse;
import com.hangahae.st.demo.dto.LectureDto;
import com.hangahae.st.demo.entity.Lecture;
import com.hangahae.st.demo.entity.RegistrationStatus;
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
        String userId = request.getUserId();
        enrollmentService.registerEnrollmentStatus(lectureId, userId, RegistrationStatus.REGISTERING);

        try {
            lectureService.applyLecture(lectureId);
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
