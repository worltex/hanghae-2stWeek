package com.hangahae.st.demo.serive;

import com.hangahae.st.demo.entity.Lecture;
import org.springframework.stereotype.Service;

@Service
public class LectureValidator {
    
    public void validateRegister(Lecture lecture) {
        if (lecture.getMaxEnrollment() <= lecture.getCurrentEnrollment()) {
            throw new RuntimeException("정원 초과되었습니다.");
        }
    }
}
