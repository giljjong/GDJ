DROP TABLE LECTURE;
DROP TABLE ENROLL;
DROP TABLE STUDENT;
DROP TABLE PROFESSOR;
DROP TABLE COURSE;

CREATE TABLE COURSE(
    CS_NO NUMBER NOT NULL,
    CS_NAME VARCHAR2(50 BYTE) NOT NULL,
    CS_CREDIT NUMBER NOT NULL
);

CREATE TABLE PROFESSOR(
    PRO_NO NUMBER NOT NULL,
    PRO_NAME VARCHAR2(20 BYTE) NOT NULL,
    PRO_MAJOR VARCHAR2(50 BYTE) NOT NULL
);

CREATE TABLE STUDENT(
    STU_NO NUMBER NOT NULL,
    STU_NAME VARCHAR2(20 BYTE) NOT NULL,
    STU_ADDR VARCHAR2(50 BYTE),
    STU_GRADE NUMBER,
    PRO_NO NUMBER
);

CREATE TABLE ENROLL(
    ENR_NO NUMBER NOT NULL,
    STU_NO NUMBER,
    CS_NO NUMBER,
    ENR_DATE DATE
);

CREATE TABLE LECTURE(
    LEC_NO NUMBER NOT NULL,
    PRO_NO NUMBER,
    ENR_NO NUMBER,
    LEC_NAME VARCHAR2(50 BYTE),
    LEC_ROOM NUMBER
);

ALTER TABLE COURSE
    ADD CONSTRAINT PK_COURSE PRIMARY KEY(CS_NO);
ALTER TABLE PROFESSOR
    ADD CONSTRAINT PK_PROFESSOR PRIMARY KEY(PRO_NO);
ALTER TABLE STUDENT
    ADD CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO);
ALTER TABLE ENROLL
    ADD CONSTRAINT PK_ENROLL PRIMARY KEY(ENR_NO);
ALTER TABLE LECTURE
    ADD CONSTRAINT PK_LECTURE PRIMARY KEY(LEC_NO);
    
ALTER TABLE STUDENT
    ADD CONSTRAINT FK_STUDENT_PROFESSOR FOREIGN KEY(PRO_NO)
        REFERENCES PROFESSOR(PRO_NO)
            ON DELETE SET NULL;
ALTER TABLE ENROLL
    ADD CONSTRAINT FK_ENROLL_STUDENT FOREIGN KEY(STU_NO)
        REFERENCES STUDENT(STU_NO)
            ON DELETE SET NULL;
ALTER TABLE ENROLL
    ADD CONSTRAINT FK_ENROLL_COURSE FOREIGN KEY(CS_NO)
        REFERENCES COURSE(CS_NO)
            ON DELETE SET NULL;
ALTER TABLE LECTURE
    ADD CONSTRAINT FK_LECTURE_PROFESSOR FOREIGN KEY(PRO_NO)
        REFERENCES PROFESSOR(PRO_NO)
            ON DELETE SET NULL;
ALTER TABLE LECTURE
    ADD CONSTRAINT FK_LECTURE_ENROLL FOREIGN KEY(ENR_NO)
        REFERENCES ENROLL(ENR_NO)
            ON DELETE SET NULL;
        
INSERT INTO COURSE
VALUES (1, '참신한 수학', 2);
INSERT INTO COURSE
VALUES (2, '쉽게 따라해요 사회성', 2);
INSERT INTO COURSE
VALUES (3, '숨 쉬는 듯 읽는 독서', 3);
INSERT INTO COURSE
VALUES (4, '잠이 솔솔오는 모코코', 3);
INSERT INTO COURSE
VALUES (5, '개꿀 교양', 2);

INSERT INTO PROFESSOR
VALUES (11, '김갑수', '한국무용');
INSERT INTO PROFESSOR
VALUES (22, '박준철', '전자파');
INSERT INTO PROFESSOR
VALUES (33, '곽두팔', '서양 미술');
INSERT INTO PROFESSOR
VALUES (44, '준식LEE', '식품생명공학');

INSERT INTO STUDENT(S_NO, S_NAME, S_ADDRESS, S_GRADE_NO, P_NO) VALUES (10101, '김학생', '서울', 1, 3);
INSERT INTO STUDENT(S_NO, S_NAME, S_ADDRESS, S_GRADE_NO, P_NO) VALUES (10102, '이학생', '경기', 1, 3);
INSERT INTO STUDENT(S_NO, S_NAME, S_ADDRESS, S_GRADE_NO, P_NO) VALUES (10103, '최학생', '인천', 1, 3);

-- 4. ENROLL 테이블 데이터 입력
INSERT INTO ENROLL(E_NO, S_NO, C_NO, E_DATE) VALUES (111, 10101, 11, '20-02-25');
INSERT INTO ENROLL(E_NO, S_NO, C_NO, E_DATE) VALUES (222, 10101, 22, '20-02-26');
INSERT INTO ENROLL(E_NO, S_NO, C_NO, E_DATE) VALUES (333, 10101, 33, '20-02-27');

-- 5. LECTURE 테이블 데이터 입력
INSERT INTO LECTURE(L_NO, P_NO, E_NO, L_NAME, L_LOCATION) VALUES (1111, 1, 111, '자바완전정복', 'A101');
INSERT INTO LECTURE(L_NO, P_NO, E_NO, L_NAME, L_LOCATION) VALUES (2222, 1, 111, '자바완전정복', 'B101');
INSERT INTO LECTURE(L_NO, P_NO, E_NO, L_NAME, L_LOCATION) VALUES (3333, 1, 111, '자바완전정복', 'C101');

-- 6. 변경된 내용을 DB에 반영
COMMIT;  -- INSERT, UPDATE, DELETE 문은 COMMIT이 필요함.