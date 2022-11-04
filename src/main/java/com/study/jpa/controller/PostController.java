package com.study.jpa.controller;

import com.study.jpa.config.LoginMember;
import com.study.jpa.dto.PageRequest;
import com.study.jpa.dto.PostDto;
import com.study.jpa.entity.Member;
import com.study.jpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
	
	private final PostService postService;

	/**
	 * 게시글 상세조회
	 */
	@GetMapping("/get")
	public ResponseEntity<Object> getPostInfo(@RequestParam Long postId, @LoginMember Member member) {
		return ResponseEntity.ok(postService.getPostInfo(postId));
	}
	
	/**
	 * 게시글 목록조회
	 */
	@GetMapping("/list")
	public ResponseEntity<Object> getPostList(@LoginMember Member member, @PageableDefault(page = 1, size = 10) PageRequest pageRequest) {
		return ResponseEntity.ok(postService.getPostList(member.getId(), pageRequest.of()));
	}
	
	/**
	 * 게시글 삭제
	 */
	@DeleteMapping("/remove")
	public ResponseEntity<Map<String, Object>> deletePostInfo(@LoginMember Member member) {
		long result = postService.deletePostInfo(member.getId());
        return new ResponseEntity<>(Collections.singletonMap("deleted cnt", result), HttpStatus.OK);
	}
	
	/**
	 * 게시글 수정
	 */
	@PatchMapping("/modify")
	public ResponseEntity<Map<String, Object>> updatePostInfo(@RequestParam Long postId, @LoginMember Member member, @RequestBody PostDto postDto) {
		long result = postService.updatePostInfo(postId, postDto);
        return new ResponseEntity<>(Collections.singletonMap("updated cnt", result), HttpStatus.OK);
	}
	
	/**
	 * 게시글 생성
	 */
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> insertPostInfo(@LoginMember Member member, @RequestBody PostDto postDto) throws Exception {
		long result = postService.insertPostInfo(member.getId(), postDto);
		return new ResponseEntity<>(Collections.singletonMap("created id", result), HttpStatus.OK);
	}
}
