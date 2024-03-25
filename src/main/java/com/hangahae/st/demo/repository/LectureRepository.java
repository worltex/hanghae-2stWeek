package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lecture l where l.id = :id")
    Lecture findWithLockingById(String id);
}
