package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.entity.RegistrationStatus;
import com.hangahae.st.demo.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentValidator {

    private final EnrollmentRepository enrollmentRepository;
    private final List<RegistrationStatus> SEARCH_STATES = List.of(RegistrationStatus.REGISTERED, RegistrationStatus.REGISTERING);

    public void validateEnrollment(String lectureId, String userId) {
        if (enrollmentRepository.existsByLectureIdAndUserIdAndRegistrationStatusIn(lectureId, userId, SEARCH_STATES)) {
            throw new RuntimeException("동일한 요청이 이미 진행중입니다.");
        }
    }
}
