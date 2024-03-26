package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Enrollment;
import com.hangahae.st.demo.domain.EnrollmentId;
import com.hangahae.st.demo.domain.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    boolean existsByIdLectureIdAndIdUserId(String lectureId, String userId);

    Optional<Enrollment> findByIdLectureIdAndIdUserId(String lectureId, String userId);

    boolean existsByIdLectureIdAndIdUserIdAndRegistrationStatusIn(String lectureId, String userId, List<RegistrationStatus> status);
}
