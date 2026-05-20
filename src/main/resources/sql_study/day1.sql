-- database 생성
create database koreait_web2;
-- database 사용 
use koreait_web2;

/*
데이터베이스의 자료형
INT -> int / Integer
VARCHAR(n) -> String : 최대문자수 지정 
TEXT -> String : 길이 무제한
DATETIME -> LocalDateTime : 날짜+시간 '2026-05-19 21:31:00'
DECIMAL(p,s) -> BigDecimal : 소수점 고정 30.12$
TINYINT -> Boolean : 0 혹은 1만 저장
*/

-- 테이블(자바의 클래스와 1:1 대응) 생성
-- 제약사항 
create table product(
	product_id int not null auto_increment,
    product_name varchar(50) not null,
    price int not null,
    create_at datetime not null default current_timestamp,
    primary key(product_id)
);
/*
제약사항 & 기타
not null -> 비워두면 안된다.
primary key -> 이 row를 다른 모든 row와 구분짓는 컬럼
default -> insert할때 비워두면, 기본값으로 써라
*/
-- 데이터를 잘못 넣었을때?
-- DROP TABLE product; 테이블 통째로 없어짐. 구조도 사라짐.
-- TRUNCATE TABLE product; 구조만 살아있고, 나머지 없어짐. auto_increment도 1로 리셋

-- insert add 쿼리

insert into
	preoduct (product_name, price, creat_at)
value
	('아이스아메리카노', 2000, '2026-05-20 19:00:00');
    
-- product 테이블 전체 조회
select * from product;
 
-- 다건 insert
insert into
	preoduct (product_name, price)
values 
	('크로와상', 2000),
	('카푸치노', 2000),
	('바닐라라떼', 2000),
	('초코쿠키', 2000),
	('카페라떼', 2000);
    
-- POST entitiy에 대응되는 Table 생성 쿼리를 작성하고 실행
create table post (
	post_id int not null auto_increment,
    title varchar(100) not null,
    content text not null,
    create_at datetime not null default current_timestamp
    
    primary key(post_id)
    );
    
insert into
		post(title, contnet)
values
	('부산 여행 후기','광안리 야경이 정말 멋졌다.'),
	('MySQL 공부중',null),
	('영화 감상','어제 본 영화는 평점에 비해 별로였다.');
    
select * from post;

insert into
	post (post_id, title)
values
	(1, '테스트'); -- 자바에서는 SQLException형태로 메세지가 전파됨.
    
-- "좋아요" 캘럼을 추가해야 된다면? 
-- alter table post add column like_count int not null default 0;    