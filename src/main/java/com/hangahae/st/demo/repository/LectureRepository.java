package com.hangahae.st.demo.repository;

import com.hangahae.st.demo.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lecture l where l.id = :id")
    Optional<Lecture> findWithLockingById(@Param(value = "id") String id);
}
