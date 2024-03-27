package com.hangahae.st.demo.serive;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.hangahae.st.demo.domain.Lecture;
import com.hangahae.st.demo.dto.LectureDto;
import com.hangahae.st.demo.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public Lecture getLectureDtoByLectureId(String lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("강의 정보가 없습니다."));
    }
    
    @Transactional
    public void updateLectureEnrollment(String lectureId) {
        Lecture lecture = lectureRepository.findWithLockingById(lectureId).orElseThrow(() -> new RuntimeException("강의 정보가 없습니다."));
        if (lecture.getCurrentEnrollment() + 1 > lecture.getMaxEnrollment()) {
            throw new RuntimeException("정원 초과");
        }
        lecture.updateCurrentEnrollment(lecture.getCurrentEnrollment() + 1);
    }

    public Lecture createLecture(int maxEnrollment, int currentEnrollment) {
        return lectureRepository.save(new Lecture(Integer.toString(RandomUtil.getPositiveInt()), ZonedDateTime.now(), maxEnrollment, currentEnrollment));
    }

    public List<LectureDto> getLectureList(ZonedDateTime lectureDate) {
        if (lectureDate != null) {
            return lectureRepository.findAllByLectureDate(lectureDate).stream().map(lecture -> new LectureDto(lecture.getLectureDate(), lecture.getMaxEnrollment(), lecture.getCurrentEnrollment())).toList();
        }
        return lectureRepository.findAll().stream().map(lecture -> new LectureDto(lecture.getLectureDate(), lecture.getMaxEnrollment(), lecture.getCurrentEnrollment())).toList();
    }
}
