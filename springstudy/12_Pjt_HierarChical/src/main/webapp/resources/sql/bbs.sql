-- 계층형(Hierarchical) 게시판

-- 상세보기 없음
-- 댓글/대댓글 가능
-- 삭제기능이 UPDATE로 처리

DROP SEQUENCE BBS_SEQ;
CREATE SEQUENCE BBS_SEQ NOCACHE;

DROP TABLE BBS;
CREATE TABLE BBS (
	BBS_NO   NUMBER 			NOT NULL,
	WRITER   VARCHAR2(64 BYTE)	NOT NULL,
	TITLE    VARCHAR2(1000 BYTE) NOT NULL,		-- 제목이 곧 내용
	IP	     VARCHAR2(30 BYTE) 	NOT NULL,
	CREATE_DATE DATE 			NOT NULL,
	STATE    NUMBER(1) 			NOT NULL,	/* 정상 : 1, 삭제 : 0 */
	DEPTH    NUMBER(2) 			NOT NULL,	/* 원글 : 0, 1차 댓글 : 1, 2차댓글 : 2, ... */
	GROUP_NO NUMBER 			NOT NULL,	/* 원글과 모든 댓글은 같은 GROUP_NO, 원글 : BBS_NO, 댓글 : 원글의 BBS_NO */
	GROUP_ORDER NUMBER 			NOT NULL,	/* 동일 그룹 내 표시 순서 */
	CONSTRAINT PK_BBS PRIMARY KEY(BBS_NO)
);

INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자1', '제목1', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자2', '제목2', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자3', '제목3', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자4', '제목4', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자5', '제목5', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자6', '제목6', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자7', '제목7', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자8', '제목8', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자9', '제목9', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자10', '제목10', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);

INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자11', '제목11', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자12', '제목12', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자13', '제목13', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자14', '제목14', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자15', '제목15', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자16', '제목16', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자17', '제목17', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자18', '제목18', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자19', '제목19', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자20', '제목20', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);

INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자21', '제목21', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자22', '제목22', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자23', '제목23', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자24', '제목24', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자25', '제목25', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자26', '제목26', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자27', '제목27', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자28', '제목28', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자29', '제목29', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자30', '제목30', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);

INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자31', '제목31', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자32', '제목32', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자33', '제목33', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자34', '제목34', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자35', '제목35', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자36', '제목36', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자37', '제목37', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자38', '제목38', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자39', '제목39', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자40', '제목40', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);

INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자41', '제목41', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자42', '제목42', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자43', '제목43', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자44', '제목44', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자45', '제목45', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자46', '제목46', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자47', '제목47', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자48', '제목48', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자49', '제목49', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);
INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자50', '제목50', '0:0:0:0:0:0:1', SYSDATE, 1, 0, BBS_SEQ.CURRVAL, 0);

