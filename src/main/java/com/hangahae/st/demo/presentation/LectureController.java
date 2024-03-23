package com.hangahae.st.demo.presentation;

import org.springframework.web.bind.annotation.*;

@RestController("/lecture")
public class LectureController {

    @PostMapping("/{lectureId}")
    public void applyLecture(@RequestBody ApplyLectureRequest request){
        //1. 강의 등록한 인원 가능한 인원 조회
        //1.2 해당 강의에 등록 등록 대기중 및 등록 완료한 인원 count
        //1.3 30명 넘은 경우 에러 처리


        //2. 동일한 신청자의 중복 요청이 있는지 확인
        //2.1 동일한 요청이 있는 경우 에러 처리
        //2.2 없는 경우 Enrollment 테이블에 status REGISTERING


        //3. 자기 자신의 요청 + REGISTERED 된 인원이 최대인원보다 큰지 확인
        //3.1 큰 경우 FAILED 처리 혹은 row 삭제 처리 => 에러 처리
        //3.2 작은 경우 COMPLETED 처리

    }

    @GetMapping("/{lectureId}")
    public ApplyLectureResponse getLectureRegistrationResult(@RequestParam long userId){
        //1.enrollment 테이블에 userId로 REGISTERED가 있는지 확인

        //있으면 true

        //없으면 false
        return new ApplyLectureResponse(userId, true);

    }

}
