package com.study.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.jpa.config.exception.ApiException;
import com.study.jpa.config.exception.ErrorCode;
import com.study.jpa.dto.PostDto;
import com.study.jpa.entity.Member;
import com.study.jpa.entity.Post;
import com.study.jpa.repository.MemberRepository;
import com.study.jpa.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public PostDto getPostInfo(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_POST));
		post.addHits(post.getHits());
		postRepository.save(post);
		
		return PostDto.of(post);
	}
	
	public List<PostDto> getPostList(Long memberId, Pageable pageable) {
		return postRepository.getPostList(memberId, pageable);
	}
	
	@Transactional
	public long deletePostInfo(Long memberId) {
		return postRepository.deletePostInfo(memberId);
	}
	
	@Transactional
	public long updatePostInfo(Long postId, PostDto postDto) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_POST));
		post.update(postDto.getTitle(), postDto.getContents());
		
		//JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 자동으로 반영
		//상태 변경 검사의 대상은 영속성 컨텍스트가 관리하는 엔티티에만 적용
		return postRepository.save(post).getId();
	}
	
	@Transactional
	public long insertPostInfo(Long memberId, PostDto postDto) throws Exception {
		Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
		Post post = postDto.toEntity();
		post.addMember(member);

		return postRepository.save(post).getId();
	}
}
