package com.study.jpa.repository;

import com.study.jpa.dto.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

	public PostDto getPostInfo(Long postId, Long memberId);
	
	public List<PostDto> getPostList(Long memberId, Pageable pageable);
	
	public long deletePostInfo(Long memberId);
	
	public long updatePostInfo(Long postId, Long memberId, PostDto postDto);
}
