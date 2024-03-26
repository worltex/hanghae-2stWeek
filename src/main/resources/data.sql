CREATE TABLE Lecture (
    lecture_id VARCHAR(255) NOT NULL,
    current_enrollment INTEGER,
    lecture_date TIMESTAMP(6) WITH TIME ZONE,
    max_enrollment INTEGER,
    PRIMARY KEY (lecture_id)
);

CREATE TABLE Enrollment (
    user_id VARCHAR(255) NOT NULL,
    lecture_id VARCHAR(255) NOT NULL,
    registration_status VARCHAR(255) CHECK (registration_status IN ('REGISTERING', 'REGISTERED', 'FAILED')),
    PRIMARY KEY (lecture_id, user_id),
    FOREIGN KEY (lecture_id) REFERENCES Lecture(lecture_id)
);

INSERT INTO lecture(lecture_id, current_enrollment, lecture_date, max_enrollment)
VALUES ('1', 0, '2024-03-25 13:46:07.802371+09', 1);
