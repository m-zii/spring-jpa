package com.study.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.jpa.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.study.jpa.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public PostDto getPostInfo(Long postId, Long memberId) {
		return jpaQueryFactory
				.select(Projections.fields(PostDto.class,
						post.title,
						post.contents,
						post.hits
						))
				.from(post)
				.where(post.id.eq(postId), post.member.id.eq(memberId))
				.fetchOne();
	}

	@Override
	public List<PostDto> getPostList(Long memberId, Pageable pageable) {
		//커버링 인덱스로 대상 조회
        List<Long> ids = jpaQueryFactory
        		.select(post.id)
        		.from(post)
        		.where(post.member.id.eq(memberId))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
                .fetch();

		//대상이 없을 경우 추가 쿼리 수행 할 필요 없이 바로 반환
		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<>();
		}

		return jpaQueryFactory
				.select(Projections.fields(PostDto.class,
						post.title, 
						post.contents,
						post.hits))
				.from(post)
				.where(post.id.in(ids))
				.orderBy(post.id.desc())
				.fetch();
	}

	@Override
	public long deletePostInfo(Long memberId) {
		return jpaQueryFactory.delete(post).where(post.member.id.eq(memberId)).execute();
	}

	@Override
	public long updatePostInfo(Long postId, Long memberId, PostDto postDto) {
		return jpaQueryFactory
				.update(post).set(post.title, postDto.getTitle())
				.set(post.contents, postDto.getContents())
				.where(post.id.eq(postId), post.member.id.eq(memberId))
				.execute();
	}
}
