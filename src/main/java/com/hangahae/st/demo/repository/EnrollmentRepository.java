package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Enrollment;
import com.hangahae.st.demo.domain.EnrollmentId;
import com.hangahae.st.demo.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
}
