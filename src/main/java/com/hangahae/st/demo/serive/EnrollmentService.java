package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional(readOnly = true)
    public boolean existEnrollmentByLectureIdAndUserId(String lectureId, String userId) {
        return enrollmentRepository.findByIdLectureIdAndIdUserId(lectureId, userId).isPresent();
    }
}
