package com.hangahae.st.demo;

import com.hangahae.st.demo.entity.RegistrationStatus;
import com.hangahae.st.demo.repository.EnrollmentRepository;
import com.hangahae.st.demo.serive.EnrollmentService;
import com.hangahae.st.demo.serive.EnrollmentValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @InjectMocks
    EnrollmentService enrollmentService;

    @Mock
    EnrollmentValidator enrollmentValidator;

    @Mock
    EnrollmentRepository enrollmentRepository;

    @Test
    public void 동일한_유저의_동일한_특강_신청_요청_있는_경우() {
        //given
        String lectureId = "1";
        String userId = "1";
        when(enrollmentRepository.existsByLectureIdAndUserId(any(), any())).thenReturn(true);

        //when
        boolean result = enrollmentService.existEnrollmentByLectureIdAndUserId(lectureId, userId);

        //then
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void 특강신청_상태_저장시_동일요청_있는_경우() {
        //given
        String lectureId = "1";
        String userId = "1";
        doThrow(RuntimeException.class).when(enrollmentValidator).validateEnrollment(any(), any());

        //when & then
        assertThrows(RuntimeException.class, () -> enrollmentService.registerEnrollmentStatus(lectureId, userId, RegistrationStatus.REGISTERING));
    }

    @Test
    public void 특강신청_상태_저장시_동일요청_없는_경우() {
        //given
        String lectureId = "1";
        String userId = "1";
        doNothing().when(enrollmentValidator).validateEnrollment(any(), any());

        //when
        enrollmentService.registerEnrollmentStatus(lectureId, userId, RegistrationStatus.REGISTERING);

        //then
        verify(enrollmentRepository, times(1)).save(any());
    }

}
