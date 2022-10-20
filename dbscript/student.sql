DROP TABLE USERS CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE BOARD CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
  USERID VARCHAR2(15) CONSTRAINT PK_MEMBER_UID PRIMARY KEY,
  USERPWD VARCHAR2(100) NOT NULL,
  USERNAME VARCHAR2(20) NOT NULL,
  GENDER CHAR(1)  NOT NULL,
  AGE NUMBER(3)   NOT NULL,
  PHONE VARCHAR2(13),
  EMAIL VARCHAR2(30),
  HOBBY VARCHAR2(100),
  ETC  VARCHAR2(1000),
  ENROLL_DATE DATE DEFAULT SYSDATE,
  LASTMODIFIED DATE DEFAULT SYSDATE  
);

CREATE TABLE USERS (
  USERID VARCHAR2(15) PRIMARY KEY,
  USERPWD VARCHAR2(15) NOT NULL,
  USERNAME VARCHAR2(20) NOT NULL
);

ALTER TABLE USERS
DROP PRIMARY KEY;

-- 삭제룰을 추가한 새 제약조건 추가함
ALTER TABLE USERS
ADD CONSTRAINT FK_MEMBER_UID 
FOREIGN KEY(USERID) REFERENCES MEMBER ON DELETE CASCADE;

-- TRIGGER 작성 : 이름 - TRI_INSERT_USERS
-- MEMBER 테이블에 새 회원정보가 기록되면, 자동으로 USERS 테이블에 아이디, 암호, 이름이
-- INSERT 되게 함
CREATE OR REPLACE TRIGGER TRI_INSERT_USERS
AFTER INSERT ON MEMBER
FOR EACH ROW
BEGIN
  INSERT INTO USERS
  VALUES (:NEW.USERID, :NEW.USERPWD, :NEW.USERNAME);
END;
/

CREATE OR REPLACE TRIGGER TRI_UPDATE_USERS
AFTER UPDATE ON MEMBER
FOR EACH ROW
BEGIN
  UPDATE USERS 
  SET USERPWD = :NEW.USERPWD
  WHERE USERID = :OLD.USERID;
END;
/

INSERT INTO MEMBER VALUES ('admin', 'admin', '관리자', 'M', 35, '010-1111-9999', 
'admin@ncs.kr', 'game,reading,music', '관리자입니다.', to_date('2016-06-25', 'RRRR-MM-DD'), 
sysdate);

INSERT INTO MEMBER
VALUES ('user01', 'pass01', '홍길동', 'M', 25, '010-1234-5678', 'hong1234@ncs.kr', 
        'climb,sport', '성격이 밝고 움직이는 활동을 좋아합니다.', default, default);
        
INSERT INTO MEMBER
VALUES ('user02', 'pass02', '신사임당', 'F', 45, '010-4545-9999', 'dano99@ncs.kr', 
        'reading,music', '조용한 편이고, 꼼꼼한 성격입니다.', default, default);
        

COMMIT;

-- notice 테이블
DROP TABLE NOTICE;

CREATE TABLE NOTICE(
  NOTICENO NUMBER CONSTRAINT PK_NOTICENO PRIMARY KEY,
  NOTICETITLE VARCHAR2(50) NOT NULL,
  NOTICEDATE DATE DEFAULT SYSDATE,
  NOTICEWRITER VARCHAR2(15) NOT NULL,
  NOTICECONTENT VARCHAR2(2000),
  FILEPATH VARCHAR2(100),
  CONSTRAINT FK_NOTICEWRITER FOREIGN KEY (NOTICEWRITER) 
      REFERENCES MEMBER ON DELETE CASCADE
);

ALTER TABLE NOTICE
DROP COLUMN FILEPATH;

ALTER TABLE NOTICE
ADD (ORIGINAL_FILEPATH VARCHAR2(100));

ALTER TABLE NOTICE
ADD (RENAME_FILEPATH VARCHAR2(100));

COMMIT;

INSERT INTO NOTICE VALUES (1, '공지 서비스 오픈', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), 
'공지 서비스 오픈2', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈3', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈4', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈5', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈6', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈7', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈8', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈9', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '공지 서비스 오픈10', SYSDATE, 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '신입사원 모집공고', 
TO_DATE('2016-07-15', 'RRRR-MM-DD'), 'user01', 
'공지 서비스가 오픈되었습니다. 많이 이용해 주세요.', null, null);
INSERT INTO NOTICE VALUES ((select max(noticeno) + 1 from notice), '신입사원 모집공고 마감', 
TO_DATE('2016-07-20', 'RRRR-MM-DD'), 'user01', 
'2016년 7월 20일 18시에 신입사원 모집을 마감합니다.', null, null);

SELECT * FROM NOTICE;
 
commit;



-- BOARD TABLE SCRIPT FILE
DROP TABLE BOARD;

CREATE TABLE BOARD(
	BOARD_NUM	NUMBER,	
	BOARD_WRITER	 VARCHAR2(20) NOT NULL,
	BOARD_TITLE	VARCHAR2(50) NOT NULL,
	BOARD_CONTENT	VARCHAR2(2000) NOT NULL,
	BOARD_ORIGINAL_FILENAME	VARCHAR2(100),
    BOARD_RENAME_FILENAME VARCHAR2(100),
    BOARD_REF NUMBER,
	BOARD_REPLY_REF	NUMBER,
	BOARD_LEV	NUMBER DEFAULT 1,
	BOARD_REPLY_SEQ NUMBER DEFAULT 1,
	BOARD_READCOUNT	NUMBER DEFAULT 0,
	BOARD_DATE	DATE DEFAULT SYSDATE,
  CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM),
  CONSTRAINT FK_BOARD_WRITER FOREIGN KEY (BOARD_WRITER) REFERENCES MEMBER (USERID) ON DELETE SET NULL
);

COMMENT ON COLUMN BOARD.BOARD_NUM IS '게시글번호';
COMMENT ON COLUMN BOARD.BOARD_WRITER IS '작성자아이디';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글제목';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글내용';
COMMENT ON COLUMN BOARD.BOARD_DATE IS '작성날짜';
COMMENT ON COLUMN BOARD.BOARD_ORIGINAL_FILENAME IS '원본첨부파일명';
COMMENT ON COLUMN BOARD.BOARD_RENAME_FILENAME IS '바뀐첨부파일명';
COMMENT ON COLUMN BOARD.BOARD_REF IS '원글번호';  -- 원글번호 : 원글-자기번호, 댓글/대댓글 : 원글번호
COMMENT ON COLUMN BOARD.BOARD_REPLY_REF IS '참조답글번호';  -- 원글 : null, 원글의 답/댓글 : 자기번호, 답글의 답글 : 참조답글번호
COMMENT ON COLUMN BOARD.BOARD_LEV IS '답글단계'; -- 원글 : 1, 원글의 답글 : 2, 답글의 답글 : 3
COMMENT ON COLUMN BOARD.BOARD_REPLY_SEQ IS '답글순번'; -- 원글 : 0, 같은 원글의 답글일 때 : 1 ....... 순차처리

INSERT INTO BOARD 
VALUES (1, 'admin', '관리자 게시글', '저희 사이트를 이용해 주셔서 감사합니다.', 
NULL, NULL, 1, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (2, 'user01', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 2, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (3, 'user02', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 3, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (4, 'user01', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 4, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (5, 'user01', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 5, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (6, 'user02', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 6, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (7, 'user01', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 7, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (8, 'user01', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 8, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (9, 'user02', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 9, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (10, 'user01', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 10, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (11, 'user01', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 11, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (12, 'user02', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 12, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (13, 'user01', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 13, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

SELECT * FROM BOARD;

COMMIT;

-- 컬럼 추가 및 제약조건 추가 : MEMBER 테이블
-- 로그인 제한여부 설정 컬럼 추가
ALTER TABLE MEMBER
ADD (LOGIN_OK CHAR(1) DEFAULT 'Y');

SELECT * FROM MEMBER;

ALTER TABLE MEMBER
ADD CONSTRAINT CHK_MEMB_LOK CHECK (LOGIN_OK IN ('Y', 'N'));

-- 관리자와 회원 구분용 컬럼 추가
ALTER TABLE MEMBER
ADD (ADMIN CHAR(1) DEFAULT 'N');
-- 'Y' : 관리자, 'N' : 회원

SELECT * FROM MEMBER;

-- 관리자인 'admin' 인 아이디의 컬럼값 변경
UPDATE MEMBER
SET ADMIN = 'Y'
WHERE USERID = 'admin';

commit;

select * from member;

-- notice 의 공지글 등록은 관리자만 등록/수정할 수 있게 처리함
-- 공지글 등록자 아이디를 수정 : user01 >> admin 으로 변경
UPDATE NOTICE
SET NOTICEWRITER = 'admin';

commit;

select * from notice;
