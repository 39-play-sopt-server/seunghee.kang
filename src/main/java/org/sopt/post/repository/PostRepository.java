package org.sopt.post.repository;

import org.sopt.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findAll();

     void deleteById(Long id);

    boolean existsByMemberId(Long memberId);

    @EntityGraph(attributePaths = "member")
    Page<Post> findAll(Pageable pageable);
}
