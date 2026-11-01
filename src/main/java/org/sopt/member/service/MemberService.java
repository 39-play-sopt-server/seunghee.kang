package org.sopt.member.service;

import org.sopt.global.response.ErrorCode;
import org.sopt.member.domain.Member;
import org.sopt.member.exception.DuplicateEmailException;
import org.sopt.member.exception.MemberHasPostsException;
import org.sopt.member.exception.MemberNotFoundException;
import org.sopt.member.repository.MemberRepository;
import org.sopt.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public MemberService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Long createMember(String name, String email) {
        Member member = new Member(name, email);
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
        return memberRepository.save(member).getId();
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        if (postRepository.existsByMemberId(memberId)) {
            throw new MemberHasPostsException(ErrorCode.MEMBER_HAS_POSTS);
        }
        memberRepository.delete(member);
    }
}