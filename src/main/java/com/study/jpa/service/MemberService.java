package com.study.jpa.service;

import com.study.jpa.config.exception.ApiException;
import com.study.jpa.config.exception.ErrorCode;
import com.study.jpa.entity.Member;
import com.study.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_USER));
    }
}
