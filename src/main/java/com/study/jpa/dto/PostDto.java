package com.study.jpa.dto;

import com.study.jpa.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
	
	String title;
	String contents;
	int hits;
	
	@Builder
	public PostDto(String title, String contents, int hits) {
		this.title = title;
		this.contents = contents;
		this.hits = hits;
	}
	
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.contents(contents)
				.hits(hits)
				.build();
	}
	
	public static PostDto of(final Post post) {
        return new PostDto(post.getTitle(), post.getContents(), post.getHits());
    }
}
