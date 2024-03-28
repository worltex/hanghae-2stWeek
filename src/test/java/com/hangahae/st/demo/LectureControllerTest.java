package com.hangahae.st.demo;

import com.hangahae.st.demo.controller.LectureController;
import com.hangahae.st.demo.controller.request.ApplyLectureRequest;
import com.hangahae.st.demo.controller.request.CreateLectureRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LectureControllerTest {


    @Autowired
    LectureController lectureController;

    String lectureId = null;

    @BeforeEach
    public void beforeEach() {
        CreateLectureRequest request = new CreateLectureRequest(3, 0);
        lectureId = lectureController.createLecture(request).getId();
    }

    @Test
    public void 특강_신청_동시_요청_모두_성공() throws InterruptedException {
        int numberOfThread = 3;

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        CountDownLatch doneSignal = new CountDownLatch(numberOfThread);
        ExecutorService executorService = new ScheduledThreadPoolExecutor(numberOfThread);

        //when
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("1"));
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("2"));
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("3"));
        doneSignal.await();
        executorService.shutdown();

        //then
        assertAll(
                () -> assertThat(successCount.get()).isEqualTo(3),
                () -> assertThat(failCount.get()).isEqualTo(0)
        );
    }

    @Test
    public void 특강_신청_동시_요청_정원_초과_실패1건() throws InterruptedException {
        int numberOfThread = 4;

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        CountDownLatch doneSignal = new CountDownLatch(numberOfThread);
        ExecutorService executorService = new ScheduledThreadPoolExecutor(numberOfThread);

        //when
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("1"));
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("2"));
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("3"));
        applyLecture(successCount, failCount, doneSignal, executorService, new ApplyLectureRequest("4"));
        doneSignal.await();
        executorService.shutdown();

        //then
        assertAll(
                () -> assertThat(successCount.get()).isEqualTo(3),
                () -> assertThat(failCount.get()).isEqualTo(1)
        );
    }


    private void applyLecture(AtomicInteger successCount, AtomicInteger failCount, CountDownLatch doneSignal, ExecutorService executorService, ApplyLectureRequest request) {
        executorService.submit(() -> {
            try {
                lectureController.applyLecture(lectureId, request);
                successCount.getAndIncrement();
            } catch (Exception e) {
                failCount.getAndIncrement();
            } finally {
                doneSignal.countDown();
            }
        });
    }
}
