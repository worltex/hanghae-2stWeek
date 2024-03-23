package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Enrollment;
import com.hangahae.st.demo.domain.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    Optional<Enrollment> findByIdLectureIdAndIdUserId(String lectureId, String userId);
}
