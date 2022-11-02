package com.study.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
		name = "POST_SEQ_GENERATOR",
		sequenceName = "POST_SEQ",
		initialValue = 1, allocationSize = 1)
public class Post extends BaseTimeEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GENERATOR")
	@Column(name = "post_id")
	private Long id;
	
	@Column(length = 200)
	private String title;
	
	@Lob
	private String contents;
	
	private int hits;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	public void addMember(Member member) {
		this.member = member;
		member.getPosts().add(this);
    }

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
	public void addHits(int hits) {
		this.hits = hits + 1;
	}
	
	@Builder
	public Post(Long id, String title, String contents, int hits, Member member) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.hits = hits;
		this.member = member;
	}
}
