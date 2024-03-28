package com.hangahae.st.demo;

import com.hangahae.st.demo.dto.LectureDto;
import com.hangahae.st.demo.entity.Lecture;
import com.hangahae.st.demo.repository.LectureRepository;
import com.hangahae.st.demo.serive.LectureService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    public void 특강_참여_실패() {
        //given
        String lectureId = "1";
        int MAX_ENROLLMENT = 30;
        int CURRENT_ENROLLMENT = 30;
        Lecture lecture = new Lecture(lectureId, ZonedDateTime.now(), MAX_ENROLLMENT, CURRENT_ENROLLMENT);
        when(lectureRepository.findWithLockingById(any())).thenReturn(Optional.of(lecture));

        //when & then
        assertThrows(RuntimeException.class, () -> lectureService.applyLecture(lectureId));
    }

    @Test
    public void 특강_목록_조회_성공_조회_날짜_있음() {
        //given
        String lectureId = "1";
        int MAX_ENROLLMENT = 30;
        int CURRENT_ENROLLMENT = 0;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Lecture lecture = new Lecture(lectureId, ZonedDateTime.now(), MAX_ENROLLMENT, CURRENT_ENROLLMENT);
        when(lectureRepository.findAllByLectureDate(any())).thenReturn(List.of(lecture));

        //when
        List<LectureDto> list = lectureService.getLectureList(zonedDateTime);

        //then
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getMaxEnrollment()).isEqualTo(MAX_ENROLLMENT);
        assertThat(list.get(0).getCurrentEnrollment()).isEqualTo(CURRENT_ENROLLMENT);
    }

    @Test
    public void 특강_목록_조회_성공_조회_날짜_없음() {
        //given
        String lectureId = "1";
        int MAX_ENROLLMENT = 30;
        int CURRENT_ENROLLMENT = 0;
        Lecture lecture = new Lecture(lectureId, ZonedDateTime.now(), MAX_ENROLLMENT, CURRENT_ENROLLMENT);
        when(lectureRepository.findAll()).thenReturn(List.of(lecture));

        //when
        List<LectureDto> list = lectureService.getLectureList(null);

        //then
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getMaxEnrollment()).isEqualTo(MAX_ENROLLMENT);
        assertThat(list.get(0).getCurrentEnrollment()).isEqualTo(CURRENT_ENROLLMENT);
    }
}
