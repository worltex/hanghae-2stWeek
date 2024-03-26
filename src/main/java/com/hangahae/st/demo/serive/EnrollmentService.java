package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.domain.Enrollment;
import com.hangahae.st.demo.domain.EnrollmentId;
import com.hangahae.st.demo.domain.Lecture;
import com.hangahae.st.demo.domain.RegistrationStatus;
import com.hangahae.st.demo.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public boolean existEnrollmentByLectureIdAndUserId(String lectureId, String userId) {
        return enrollmentRepository.existsByIdLectureIdAndIdUserId(lectureId, userId);
    }

    @Transactional
    public void saveEnrollmentStatus(Lecture lecture, String userId, RegistrationStatus status) {
        String lectureId = lecture.getId();
        boolean isExist = enrollmentRepository.existsByIdLectureIdAndIdUserIdAndRegistrationStatusIn(lectureId, userId, List.of(RegistrationStatus.REGISTERED, RegistrationStatus.REGISTERING));
        if (isExist) {
            throw new RuntimeException("동일한 요청이 이미 진행중입니다.");
        }
        enrollmentRepository.save(new Enrollment(new EnrollmentId(userId, lectureId), lecture, status));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateEnrollmentStatus(String lecture, String userId, RegistrationStatus status) {
        Enrollment enrollment = enrollmentRepository.findByIdLectureIdAndIdUserId(lecture, userId).orElseThrow(() -> new RuntimeException("등록 정보가 없습니다."));
        enrollment.updateRegistrationStatus(status);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteEnrollment(String lectureId, String userId) {
        enrollmentRepository.deleteById(new EnrollmentId(userId, lectureId));
    }

}
