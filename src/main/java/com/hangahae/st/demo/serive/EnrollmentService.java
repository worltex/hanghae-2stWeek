package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.entity.Enrollment;
import com.hangahae.st.demo.entity.RegistrationStatus;
import com.hangahae.st.demo.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentValidator enrollmentValidator;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public boolean existEnrollmentByLectureIdAndUserId(String lectureId, String userId) {
        return enrollmentRepository.existsByLectureIdAndUserId(lectureId, userId);
    }

    @Transactional
    public void registerEnrollmentStatus(String lectureId, String userId, RegistrationStatus status) {
        enrollmentValidator.validateEnrollment(lectureId, userId);

        enrollmentRepository.save(new Enrollment(userId, lectureId, status));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateEnrollmentStatus(String lecture, String userId, RegistrationStatus status) {
        Enrollment enrollment = enrollmentRepository.findByLectureIdAndUserId(lecture, userId).orElseThrow(() -> new RuntimeException("등록 정보가 없습니다."));
        enrollment.updateRegistrationStatus(status);
    }

}
