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
, managerid    varchar2(50)                           -- 직속상사사번
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

select * from tbl_employee;

create sequence seq_tbl_position_pcode
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_POSITION_PCODE이(가) 생성되었습니다.

create sequence seq_tbl_department_dcode
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_DEPARTMENT_DCODE이(가) 생성되었습니다.

create sequence seq_tbl_employee_employeeid
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_EMPLOYEE_EMPLOYEEID이(가) 생성되었습니다.

--------------------------------------------------------------------------------

-- ***** 로그인 기록 테이블 생성 ***** --
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


-- ***** 출퇴근기록 테이블 ***** --
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


-- ***** 회사위치 테이블 ***** --
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

-- ***** 업무테이블 ***** --
create table tbl_todo
( fk_pNo         varchar2(50)   not null  -- 상품번호
, fk_dcode       varchar2(3)    not null  -- 부서코드
, fk_managerid   varchar2(50)   not null  -- 배정자사번
, assignDate     date                     -- 배정일
, startDate      date                     -- 시작일
, dueDate        date                     -- 기한일
, endDate        date                     -- 완료일
, fk_employeeid  varchar2(50)             -- 담당자사번
, hurryno        varchar2(2)    default 0 not null  -- 긴급여부(0:일반 1: 긴급)
, constraint PK_tbl_todo_fk_pNo primary key(fk_pNo)
, constraint FK_tbl_todo_fk_pNo foreign key(fk_pNo) references tbl_product(pNo)
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

-- ***** 상품테이블 ***** --
create table tbl_product
( pNo        varchar2(50)   not null   -- 상품번호
, pName      varchar2(100)  not null   -- 상품명
, miniNo     varchar2(50)   not null   -- 최소예약인원
, maxNo      varchar2(50)   not null   -- 최대예약인원
, nowNo      varchar2(50)   default 0  -- 현재예약인원
, startDate  date           not null   -- 여행시작일
, endDate    date           not null   -- 여행종료일
, pImage     varchar2(100)  default 'noimage.png'  -- 상품이미지
, constraint PK_tbl_product_pNo primary key(pNo)
)
-- Table TBL_PRODUCT이(가) 생성되었습니다.

-- ***** 상품테이블 시퀀스 ***** --
create sequence seq_tbl_product_pNo
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_PRODUCT_PNO이(가) 생성되었습니다.

create table tbl_client
( fk_pNo        varchar2(50)    not null  -- 상품번호
, clientmobile  varchar2(100)    not null  -- 고객 연락처
, clientname    varchar2(50)    not null  -- 고객명
, cnumber       varchar2(6)     not null  -- 인원수
, constraint PK_tbl_client primary key(fk_pNo, clientmobile)
, constraint FK_tbl_client_fk_pNo foreign key(fk_pNo) references tbl_product(pNo)  
);
-- Table TBL_CLIENT이(가) 생성되었습니다.

-- ***** 예약테이블 ***** --
create table tbl_reserve
( fk_todoNo varchar2(50)   not null  -- 업무순번
, miniNo    varchar2(50)   not null  -- 최소예약인원
, maxNo     varchar2(50)   not null  -- 최대예약인원
, nowNo     varchar2(50)   not null  -- 최대예약인원
, startDate varchar2(50)   not null  -- 여행시작일
, endDate   varchar2(50)   not null  -- 여행종료일
, constraint PK_tbl_reserve_fk_todoNo primary key(fk_todoNo)
, constraint FK_tbl_reserve_fk_todoNo foreign key(fk_todoNo) references tbl_todo(todoNo)
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

--------------------------------------------------------------------------------

-- **** 공지사항게시판 ****

create table tbl_noticeboard
(seq             number                not null    -- 글번호
,fk_catenum      number                not null    -- 카테고리번호
,fk_employeeid   varchar2(20)          not null    -- 사번
,name            varchar2(20)          not null    -- 글쓴이 
,subject         Nvarchar2(200)        not null    -- 글제목
,content         Nvarchar2(2000)       not null    -- 글내용   -- clob (최대 4GB까지 허용) 
,pw              varchar2(20)          not null    -- 글암호
,readCount       number default 0      not null    -- 글조회수
,regDate         date default sysdate  not null    -- 글쓴시간
,status          number(1) default 1   not null    -- 글삭제여부   1:사용가능한 글,  0:삭제된글
,imagefile       varchar2(200)                     -- 첨부이미지
,constraint PK_tbl_notice_seq primary key(seq)
,constraint FK_tbl_notice_fk_catenum foreign key(fk_catenum) references tbl_noticecategory(categnum)
,constraint FK_tbl_notice_fk_employeeid foreign key(fk_employeeid) references tbl_employee(employeeid)
,constraint CK_tbl_notice_status check( status in(0,1) )
);

-- **** 공지사항카테고리 ****
create table tbl_noticecategory
(categnum   number        not null
,categname  varchar2(100) not null
,constraint PK_tbl_noticecategory_categnum primary key(categnum)
);

-- **** 공지사항 글번호 시퀀스 ****
create sequence seq_noticeboard
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

--------------------------------------------------------------------------------




-- ***** 전자결재 테이블 ***** --
create table tbl_approval
( ano           number          not null                 -- 문서번호
, fk_employeeid varchar2(50)    not null                 -- 사번
, anocode       varchar2(2)     not null                 -- 결재구분 (1:일반결재, 2:지출결재, 3:근태결재)
, arecipient    varchar2(50)    not null                 -- 수신자    
, atitle        varchar2(100)   not null                 -- 제목
, astatus       varchar2(2)     default '0' not null     -- 결재상태 (0:제출, 1:결재진행중, 2:반려, 3:승인완료)
, acontent      varchar2(4000)  not null                 -- 글내용
, asdate        date            default sysdate not null -- 기안일
, afile         varchar2(500)                            -- 첨부파일
, apaper        varchar2(2)     not null                 -- 문서상태 (0:수신, 1:발신, 2:임시보관)
, constraint PK_tbl_approval_ano primary key(ano)
, constraint FK_tbl_approval_fk_employeeid foreign key(fk_employeeid) references tbl_employee(employeeid)
, constraint CK_tbl_approval_anocode check (anocode in ('1', '2', '3'))
, constraint CK_tbl_approval_astatus check (astatus in ('0', '1', '2', '3'))
, constraint CK_tbl_approval_apaper check (apaper in ('0', '1', '2'))
);
-- Table TBL_APPROVAL이(가) 생성되었습니다.

insert into tbl_approval(ano, fk_employeeid, anocode, arecipient, atitle, astatus, acontent, apaper)
values (seq_tbl_approval.nextval, 'tw001', '1', 'tw002', '회의록', '0', '회의록입니다', '1');
insert into tbl_approval(ano, fk_employeeid, anocode, arecipient, atitle, astatus, acontent, apaper)
values (seq_tbl_approval.nextval, 'tw001', '1', 'tw002', '회의록1', '0', '회의록입니다', '1');
insert into tbl_approval(ano, fk_employeeid, anocode, arecipient, atitle, astatus, acontent, apaper)
values (seq_tbl_approval.nextval, 'tw001', '1', 'tw002', '회의록2', '0', '회의록입니다', '1');

commit;

-- ***** 전자결재 시퀀스 ***** --
create sequence seq_tbl_approval
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_APPROVAL이(가) 생성되었습니다.

select *
from tbl_approval;

-- ***** 일반결재 테이블 ***** --
create table tbl_norapproval
( ncat      varchar2(2)     not null    -- 일반결재카테고리 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
, fk_ano    number          not null    -- 문서번호
, ncatname  varchar2(10)    not null    -- 일반결재카테고리명 (1:회의록, 2:위임장, 3:외부공문, 4:협조공문)
, constraint PK_tbl_norapproval_ncat_ano primary key(ncat, fk_ano)
, constraint CK_tbl_norapproval_ncat check (ncat in ('0', '1', '2', '3'))
, constraint FK_tbl_norapproval_nno foreign key(fk_ano) references tbl_approval(ano)
);
-- Table TBL_NORAPPROVAL이(가) 생성되었습니다.

-- ***** 일반결재 시퀀스 ***** --
create sequence seq_tbl_norapproval
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_NORAPPROVAL이(가) 생성되었습니다.

select *
from tbl_norapproval;

-- ***** 회의록 테이블 ***** --
create table tbl_minutes
( mno       number          not null    -- 순번
, fk_ncat   varchar2(2)     not null    -- 일반결재카테고리
, fk_ano    number          not null    -- 문서번호
, mdate     date            not null    -- 회의시간
, constraint PK_tbl_minutes_mno primary key(mno)
, constraint FK_tbl_minutes_fk_ncat foreign key(fk_ncat, fk_ano) references tbl_norapproval(ncat, fk_ano)
);
-- Table TBL_MINUTES이(가) 생성되었습니다.

-- ***** 회의록 시퀀스 ***** --
create sequence seq_tbl_minutes
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_MINUTES이(가) 생성되었습니다.

select *
from tbl_minutes;

-- ***** 위임장 테이블 ***** --
create table tbl_wiimjang
( wno           number          not null    -- 순번
, fk_ncat       varchar2(2)     not null    -- 일반결재카테고리
, fk_ano        number          not null    -- 문서번호
, fk_wiimdate   date            not null    -- 위임기간
, constraint PK_tbl_wiimjang_wno primary key(wno)
, constraint FK_tbl_wiimjang_fk_ncat foreign key(fk_ncat, fk_ano) references tbl_norapproval(ncat, fk_ano)
);
-- Table TBL_WIIMJANG이(가) 생성되었습니다.

-- ***** 위임장 시퀀스 ***** --
create sequence seq_tbl_wiimjang
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_WIIMJANG이(가) 생성되었습니다.

select *
from tbl_wiimjang;

-- ***** 외부공문 테이블 ***** --
create table tbl_exofficial
( eno       number          not null    -- 순번
, fk_ncat   varchar(2)      not null    -- 일반결재카테고리
, fk_ano    number          not null    -- 문서번호
, constraint PK_tbl_exofficial_eno primary key(eno)
, constraint FK_tbl_exofficial_fk_ncat foreign key(fk_ncat, fk_ano) references tbl_norapproval(ncat, fk_ano)
);
-- Table TBL_EXOFFICIAL이(가) 생성되었습니다.

-- ***** 외부공문 시퀀스 ***** --
create sequence seq_tbl_exofficial
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_EXOFFICIAL이(가) 생성되었습니다.

select *
from tbl_exofficial;


-- ***** 협조공문 테이블 ***** --
create table tbl_coofficial
( cno       number          not null    -- 순번
, fk_ncat   varchar(2)      not null    -- 일반결재카테고리
, fk_ano    number          not null    -- 문서번호
, comname   varchar(50)     not null    -- 타회사명
, constraint PK_tbl_coofficial_cno primary key(cno)
, constraint FK_tbl_coofficial_fk_ncat foreign key(fk_ncat, fk_ano) references tbl_norapproval(ncat, fk_ano)
);
-- Table TBL_COOFFICIAL이(가) 생성되었습니다.

-- ***** 협조공문 시퀀스 ***** --
create sequence seq_tbl_coofficial
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_COOFFICIAL이(가) 생성되었습니다.

select *
from tbl_coofficial;

-- ***** 지출결재 테이블 ***** --
create table tbl_spenda
( scat      varchar2(2)     not null    -- 지출결재카테고리 (1:지출결의서, 2:법인카드사용신청서)
, fk_ano    number          not null    -- 문서번호
, scatname  varchar2(10)    not null    -- 지출결재카테고리명 (1:지출결의서, 2:법인카드사용신청서)
, constraint PK_tbl_spenda_scat primary key(scat, fk_ano)
, constraint CK_tbl_spenda check (scat in ('1', '2'))
, constraint FK_tbl_spenda_fk_ano foreign key(fk_ano) references tbl_approval(ano)
);
-- Table TBL_SPENDA이(가) 생성되었습니다.

-- ***** 지출결재 시퀀스 ***** --
create sequence seq_tbl_spenda
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_SPENDA이(가) 생성되었습니다.

select *
from tbl_spenda;

-- ***** 지출결의서 테이블 ***** --
create table tbl_expense
( exno      number          not null    -- 순번
, fk_scat   varchar(2)      not null    -- 지출결재카테고리
, fk_ano    number          not null    -- 문서번호
, exdate    date            not null    -- 지출일자
, exprice   number          not null    -- 지출금액
, constraint PK_tbl_expense_exno primary key(exno)
, constraint FK_tbl_expense_fk_scat foreign key(fk_scat, fk_ano) references tbl_spenda(scat, fk_ano)
);
-- Table TBL_EXPENSE이(가) 생성되었습니다.

-- ***** 지출결의서 시퀀스 ***** --
create sequence seq_tbl_expense
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_EXPENSE이(가) 생성되었습니다.

select *
from tbl_expense;

-- ***** 법인카드사용신청서 테이블 ***** --
create table tbl_cocard
( cono      number          not null    -- 순번
, fk_scat   varchar(2)      not null    -- 지출결재카테고리
, fk_ano    number          not null    -- 문서번호
, codate    date            not null    -- 사용예정일
, cocardnum varchar(20)                 -- 카드번호
, copurpose varchar(20)     not null    -- 지출목적 (1:교통비, 2:사무비품, 3:주유비, 4:출장비, 5:식비, 6:기타)
, coprice   number          not null    -- 예상금액
, constraint PK_tbl_cocard_cono primary key(cono)
, constraint FK_tbl_cocard_fk_scat foreign key(fk_scat, fk_ano) references tbl_spenda(scat, fk_ano)
, constraint CK_tbl_cocard_copurpose check (copurpose in('1', '2', '3', '4', '5', '6'))
);
-- Table TBL_COCARD이(가) 생성되었습니다.

-- ***** 법인카드사용신청서 시퀀스 ***** --
create sequence seq_tbl_cocard
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_COCARD이(가) 생성되었습니다.

select *
from tbl_cocard;

-- ***** 근태결재 테이블 ***** --
create table tbl_vacation
( vno       varchar2(2)     not null    -- 근태결재카테고리 (1:병가, 2:반차, 3:연차, 4:경조휴가, 5:출장, 6:추가근무)
, fk_ano    number          not null    -- 문서번호
, vcatname  varchar2(10)    not null    -- 근태결재카테고리명 (1:병가, 2:반차, 3:연차, 4:경조휴가, 5:출장, 6:추가근무)
, constraint PK_tbl_vacation_vno primary key(vno, fk_ano)
, constraint CK_tbl_vacation check (vno in ('1', '2', '3', '4', '5', '6'))
, constraint FK_tbl_vacation_fk_ano foreign key(fk_ano) references tbl_approval(ano)
);
-- Table TBL_VACATION이(가) 생성되었습니다.

-- ***** 근태결재 시퀀스 ***** --
create sequence seq_tbl_vacation
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_VACATION이(가) 생성되었습니다.

select * 
from tbl_vacation;

-- ***** 병가 테이블 ***** --
create table tbl_sickleave
( slno      number          not null    -- 순번
, fk_vno    varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano    number          not null    -- 문서번호
, slstart   date            not null    -- 사용예정시작일자
, slend     date            not null    -- 사용예정마지막일자
, sldates   number          not null    -- 사용일수
, constraint PK_tbl_sickleave_slno primary key(slno)
, constraint FK_tbl_sickleave_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_SICKLEAVE이(가) 생성되었습니다.

-- ***** 병가 시퀀스 ***** --
create sequence seq_tbl_sickleave
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_SICKLEAVE이(가) 생성되었습니다.

select *
from tbl_sickleave;

-- ***** 반차 테이블 ***** --
create table tbl_afternoonoff
( afno      number          not null    -- 순번
, fk_vno    varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano    number          not null    -- 문서번호
, afdate    date            not null    -- 사용예정일
, afdan     number          not null    -- 오전오후 (1: 오전, 2:오후)
, afdates   number          default 0.5 not null    -- 사용일수 (0.5)
, constraint PK_tbl_afternoonoff_afno primary key(afno)
, constraint FK_tbl_afternoonoff_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_AFTERNOONOFF이(가) 생성되었습니다.

-- ***** 반차 시퀀스 ***** --
create sequence seq_tbl_afternoonoff
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_AFTERNOONOFF이(가) 생성되었습니다.

select *
from tbl_afternoonoff;

-- ***** 연차 테이블 ***** --
create table tbl_dayoff
( dayno      number          not null    -- 순번
, fk_vno     varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano     number          not null    -- 문서번호
, daystart   date            not null    -- 사용예정시작일자
, dayend     date            not null    -- 사용예정마지막일자
, daydates   number          not null    -- 사용일수
, constraint PK_tbl_dayoff_dayno primary key(dayno)
, constraint FK_tbl_dayoff_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_DAYOFF이(가) 생성되었습니다.

-- ***** 연차 시퀀스 ***** --
create sequence seq_tbl_dayoff
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_DAYOFF이(가) 생성되었습니다.

select *
from tbl_dayoff;

-- ***** 경조휴가 테이블 ***** --
create table tbl_congoff
( congno      number          not null    -- 순번
, fk_vno      varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano      number          not null    -- 문서번호
, congstart   date            not null    -- 사용예정시작일자
, congend     date            not null    -- 사용예정마지막일자
, congdates   number          not null    -- 사용일수
, constraint PK_tbl_congoff_congno primary key(congno)
, constraint FK_tbl_congoff_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_CONGOFF이(가) 생성되었습니다.

-- ***** 경조휴가 시퀀스 ***** --
create sequence seq_tbl_congoff
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_CONGOFF이(가) 생성되었습니다.

select *
from tbl_congoff;

-- ***** 출장 테이블 ***** --
create table tbl_businesstrip
( buno      number          not null    -- 순번
, fk_vno    varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano    number          not null    -- 문서번호
, bustart   date            not null    -- 사용예정시작일자
, buend     date            not null    -- 사용예정마지막일자
, budates   number          not null    -- 사용일수
, buplace   varchar2(50)                -- 출장지
, bupeople  number                      -- 출장인원
, constraint PK_tbl_businesstrip_buno primary key(buno)
, constraint FK_tbl_businesstrip_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_BUSINESSTRIP이(가) 생성되었습니다.

-- ***** 출장 시퀀스 ***** --
create sequence seq_tbl_businesstrip
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_BUSINESSTRIP이(가) 생성되었습니다.

select * 
from tbl_businesstrip;

-- ***** 추가근무 테이블 ***** --
create table tbl_extrawork
( ewno      number          not null    -- 순번
, fk_vno    varchar2(2)     not null    -- 근태결재카테고리 
, fk_ano    number          not null    -- 문서번호
, ewdate    date            not null    -- 사용예정일
, ewhours   number          not null    -- 추가근무시간
, constraint PK_tbl_extrawork_ewno primary key(ewno)
, constraint FK_tbl_extrawork_fk_vno foreign key(fk_vno, fk_ano) references tbl_vacation(vno, fk_ano)
);
-- Table TBL_EXTRAWORK이(가) 생성되었습니다.

-- ***** 추가근무 시퀀스 ***** --
create sequence seq_tbl_extrawork
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_EXTRAWORK이(가) 생성되었습니다.

select *
from tbl_extrawork;

