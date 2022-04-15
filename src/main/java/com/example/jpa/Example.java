package com.example.jpa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity // 필수(jpa를 사용한다고 인식)
//@Table(name = "USER") DB 테이블명이 다른경우
//@Table(uniqueConstraints = ) unique = true 대신 사용 많이함
//@SequenceGenerator(
//		name = "MEMBER_SEQ_GENERATOR",
//		sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 이름
//		initialValue = 1, allocationSize = 50 // DB에 50개 올려놓고 메모리에서 가져옴
//		)
public class Example {

	@Id //PK
//	private Long id; 
	@GeneratedValue(// strategy = GenerationType.IDENTITY //기본키 생성은 데이터 베이스에 위임
					strategy = GenerationType.SEQUENCE,
					generator = "MEMBER_SEQ")
	private Long id;
	
	@Column(name = "name" //DB 컬럼명이 다른경우/ 
			, insertable = true, updatable = true // insertable,updatable 기본 true
			, nullable = false //not null 
			, unique = true // 잘 사용하지 않음(이름을 반영하기 어려움)
			, columnDefinition = "varchar(100) default 'EMPTY'") 
	private String username; 
	
	private BigDecimal age; 
	
	@Enumerated(EnumType.STRING)
	private RoleType roleType; 
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createdDate; //private LocalDate testLocalDate;
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date lastModifiedDate; //private LocalDateTime testLocalDateTime;
	
	@Lob 
	private String description;
	
	@Transient //DB랑 관계없이 메모리에서만 사용
	private int temp;
	 
	public Example() {
	}

	public Example(Long id, String name) {
		this.id = id;
		this.username = name;
	}
}
