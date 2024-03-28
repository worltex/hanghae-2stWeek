package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.entity.Enrollment;
import com.hangahae.st.demo.entity.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

    boolean existsByLectureIdAndUserId(String lectureId, String userId);

    Optional<Enrollment> findByLectureIdAndUserId(String lectureId, String userId);

    boolean existsByLectureIdAndUserIdAndRegistrationStatusIn(String lectureId, String userId, List<RegistrationStatus> status);
}
