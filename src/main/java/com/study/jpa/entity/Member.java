package com.study.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		sequenceName = "MEMBER_SEQ",
		initialValue = 1, allocationSize = 1)
public class Member {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	@Column(name = "member_id")
	private Long id;
	
	@Column(length = 200)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "member")
	private List<Post> posts = new ArrayList<>();
	
	@Builder
	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
