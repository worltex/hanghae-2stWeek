package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.domain.Lecture;
import com.hangahae.st.demo.domain.RegistrationStatus;
import com.hangahae.st.demo.dto.LectureDto;
import com.hangahae.st.demo.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;
    private final EnrollmentService enrollmentService;

    @Transactional
    public LectureDto getLectureDtoByLectureId(String lectureId) {
        Lecture lecture = getLectureById(lectureId);
        return new LectureDto(lecture.getMaxEnrollment(), lecture.getCurrentEnrollment());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateLectureEnrollment(String lectureId, String userId) {
        try {
            Lecture lecture = lectureRepository.findWithLockingById(lectureId);
            if (lecture.getCurrentEnrollment() + 1 > lecture.getMaxEnrollment()) {
                throw new RuntimeException("정원 초과");
            }
            lecture.updateCurrentEnrollment(lecture.getCurrentEnrollment() + 1);
            lectureRepository.saveAndFlush(lecture);
        } catch (Exception e) {
            enrollmentService.updateEnrollmentStatus(lectureId, userId, RegistrationStatus.FAILED);
            throw e;
        }
    }

    public Lecture getLectureById(String lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("강의 정보가 없습니다."));
    }


}
