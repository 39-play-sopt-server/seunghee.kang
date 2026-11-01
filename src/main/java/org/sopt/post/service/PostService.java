package org.sopt.post.service;

import org.sopt.global.response.ErrorCode;
import org.sopt.member.domain.Member;
import org.sopt.member.exception.MemberNotFoundException;
import org.sopt.member.repository.MemberRepository;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.dto.response.PostResponse;
import org.sopt.post.exception.InvalidPostException;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private static final int MAX_PAGE_SIZE = 50;

    private final PostRepository repository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createPost(String title, String content, PostCategory category, Long memberId) {
        if (memberId == null) {
            throw new InvalidPostException(ErrorCode.POST_MEMBER_REQUIRED);
        }
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        Post post = new Post(title, content, category, member);
        return repository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public PostListResponse getPosts(int page, int size) {
        if (page < 1 || size < 1 || size > MAX_PAGE_SIZE) {
            throw new InvalidPostException(ErrorCode.INVALID_PARAMETER);
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt", "id"));
        Page<Post> postPage = repository.findAll(pageable);
        return PostListResponse.from(postPage);
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = findPost(postId);
        post.increaseViewCount();
        return PostResponse.from(post);
    }

    @Transactional
    public void updatePost(Long postId, String title, String content) {
        Post post = findPost(postId);
        post.update(title, content);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = findPost(postId);
        repository.delete(post);
    }

    private Post findPost(Long postId) {
        return repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
    }
}
