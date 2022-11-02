create table post (
   post_id bigint not null, 	-- 게시글 id
   contents clob,				-- 내용
   hits integer not null,		-- 조회수
   title varchar(200),			-- 제목
   member_id bigint,			-- 사용자 id
   reg_dtime timestamp,	  	    -- 등록일시
   upt_dtime timestamp,		    -- 수정일시
   primary key (post_id)
);

create table member (
    member_id bigint not null,	-- 사용자 id
    name varchar(200),			-- 이름
    primary key (member_id)
);

alter table post add constraint fk_member_id
foreign key (member_id) references member;
