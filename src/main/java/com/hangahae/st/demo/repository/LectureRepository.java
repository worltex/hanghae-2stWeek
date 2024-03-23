package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
