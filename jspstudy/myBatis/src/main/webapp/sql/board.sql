DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ
	START WITH 1000
	NOCACHE;

DROP TABLE BOARD;
CREATE TABLE BOARD (
	BOARD_NO 	NUMBER		 	   NOT NULL,
	NAME     	VARCHAR2(10 BYTE) NOT NULL,
	TITLE 	 	VARCHAR2(20 BYTE) NOT NULL,
	CONTENT  	VARCHAR2(400 BYTE),
	CREATE_DATE DATE,
	CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);