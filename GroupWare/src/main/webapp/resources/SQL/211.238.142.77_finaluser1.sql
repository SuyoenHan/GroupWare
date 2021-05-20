-- ***** 직급테이블 ***** --
create table tbl_position
( pcode   varchar2(3)   not null   -- 직급코드
, pname   varchar2(50)  not null   -- 직급명
, offcnt  varchar2(3)   not null   -- 기본연차수
, salary  varchar2(50)  not null   -- 기본급
, commissionpercase   varchar2(50)    not null  -- 건당성과금
, constraint PK_tbl_position_pcode primary key(pcode)
, constraint UK_tbl_position_pname unique(pname)
);
-- Table TBL_POSITION이(가) 생성되었습니다.

create sequence seq_tbl_position_pcode
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_POSITION_PCODE이(가) 생성되었습니다.

-- ***** 부서테이블 ***** --
create table tbl_department
( dcode      varchar2(3)    not null  -- 부서코드    
, dname      varchar2(50)   not null  -- 부서명
, managerid  varchar2(50)   not null  -- 부서장사번
, duty       varchar2(100)  not null  -- 직무
, constraint PK_tbl_department_dcode primary key(dcode)
, constraint UK_tbl_department_dname unique(dname)
, constraint FK_tbl_department_managerid foreign key (managerid) references tbl_employee(employeeid)
);
-- Table TBL_DEPARTMENT이(가) 생성되었습니다.

create sequence seq_tbl_department_dcode
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_DEPARTMENT_DCODE이(가) 생성되었습니다.


-- ***** 직원테이블 ***** --
create table tbl_employee
( employeeid   varchar2(50)    not null   -- 사번
, fk_dcode     varchar2(3)                -- 부서코드
, fk_pcode     varchar2(3)                -- 직급코드
, name         varchar2(50)    not null   -- 직원명
, passwd       varchar2(100)   not null   -- 비밀번호
, email        varchar2(100)   not null   -- 회사이메일
, mobile       varchar2(50)               -- 연락처   
, cmobile      varchar2(50)    not null   -- 회사연락처
, jubun        varchar2(100)   not null   -- 주민번호
, hiredate     date            default sysdate not null  -- 입사일자
, status       varchar2(2)     default '0' not null     -- 재직상태 (0:재직중, 1:퇴사)
, managerid    varchar2(50)    null   -- 직속상사사번
, employeeimg  varchar2(100)   default 'noimage.png'  -- 직원이미지
, constraint  PK_tbl_employee_employeeid primary key(employeeid)
, constraint  FK_tbl_employee_fk_dcode foreign key(fk_dcode) references tbl_department(dcode) on delete cascade
, constraint  FK_tbl_employee_fk_pcode foreign key(fk_pcode) references tbl_position(pcode) on delete cascade
, constraint  UK_tbl_employee_email unique(email)
, constraint  UK_tbl_employee_mobile unique(mobile)
, constraint  UK_tbl_employee_cmobile unique(cmobile)
, constraint  CK_tbl_employee_status check (status in ('0','1'))
);
-- Table TBL_EMPLOYEE이(가) 생성되었습니다.


create sequence seq_tbl_employee_employeeid
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_EMPLOYEE_EMPLOYEEID이(가) 생성되었습니다.

-- 로그인 기록 테이블 생성
create table login_history
( loginno        varchar2(100)   not null     -- 순번
, fk_employeeid  varchar2(50)    not null     -- 사번
, loginip        varchar2(100)   not null     -- 직원ip
, logindate      varchar2(100)   default sysdate not null --로그인 날짜 시각
, constraint  PK_login_history_loginno primary key(loginno)
, constraint  FK_login_history_fk_employeeid foreign key(fk_employeeid) references tbl_employee(employeeid) 
);
-- Table LOGIN_HISTORY이(가) 생성되었습니다.

create sequence seq_login_history_loginno
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_LOGIN_HISTORY_LOGINNO이(가) 생성되었습니다.


-- 출퇴근기록 테이블
create table tbl_inout
( gooutdate        date            not null -- 날짜
, fk_employeeid    varchar2(50)    not null -- 사번
, intime           date            not null -- 출근시간
, outtime          date            not null -- 퇴근시간
, lateno           varchar2(2)     not null -- 지각여부(0 :정상출근 1: 지각 )
, constraint PK_tbl_inout_gooutdate primary key(gooutdate)
, constraint FK_tbl_inout_fk_employeeid foreign key(fk_employeeid) references tbl_employee(employeeid) 
, constraint CK_tbl_inout_lateno check (lateno in ('0','1'))
)
-- Table TBL_INOUT이(가) 생성되었습니다.

create sequence seq_tbl_inout_gooutdate
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_INOUT_GOOUTDATE이(가) 생성되었습니다.


-- 회사위치 테이블
create table tbl_twmap 
(storeCode     varchar2(20)  not null     --  지점코드
,storeName     varchar2(100) not null     --  지점명
,storeAddress  varchar2(200)              --  지점주소
,storeImg      varchar2(200) not null     --  지점 소개이미지
,storePhone    varchar2(200) not null     --  지점전화번호
,lat           varchar2(100) not null     --  위도
,lng           varchar2(100) not null     --  경도 
,zindex        varchar2(100) not null     --  zindex 
,constraint PK_tbl_twmap_storeCode primary key(storeCode)
,constraint UQ_tbl_twmap_zindex unique(zindex)
);
-- Table TBL_TWMAP이(가) 생성되었습니다.

create sequence seq_tbl_twmap_zindex
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_TWMAP_ZINDEX이(가) 생성되었습니다.

-- 업무테이블
create table tbl_todo
( todoNo         varchar2(50)   not null -- 순번
, fk_dcode       varchar2(3)    not null  -- 부서코드
, projectName    varchar2(200)  not null -- 프로젝트명
, fk_managerid   varchar2(50)   not null -- 배정자사번
, assignDate     date                    -- 배정일
, startDate      date                    -- 시작일
, endDate        date                    -- 완료일
, fk_employeeid  varchar2(50)            -- 담당자사번
, hurryno        varchar2(2)    not null -- 긴급여부(0:일반 1: 긴급)
, constraint PK_tbl_todo_todoNo primary key(todoNo)
, constraint FK_tbl_todo_fk_dcode foreign key(fk_dcode) references tbl_department(dcode) 
, constraint FK_tbl_todo_fk_managerid foreign key(fk_managerid) references tbl_employee(employeeid) 
, constraint FK_tbl_todo_fk_employeeid foreign key(fk_employeeid) references tbl_employee(employeeid)
, constraint CK_tbl_todo_hurryno check(hurryno in ('0','1'))
)
-- Table TBL_TODO이(가) 생성되었습니다.


create sequence seq_tbl_todo_todoNo
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_TODO_TODONO이(가) 생성되었습니다.

-- 예약테이블
create table tbl_reserve
( reserveNo varchar2(50)   not null -- 순번
, miniNo    varchar2(50)   not null -- 최소예약인원
, maxNo     varchar2(50)   not null -- 최대예약인원
, nowNo     varchar2(50)   not null -- 최대예약인원
, constraint PK_tbl_reserve_reserveNo primary key(reserveNo)
)
-- Table TBL_RESERVE이(가) 생성되었습니다.

create sequence seq_tbl_reserve_reserveNo
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_RESERVE_RESERVENO이(가) 생성되었습니다.