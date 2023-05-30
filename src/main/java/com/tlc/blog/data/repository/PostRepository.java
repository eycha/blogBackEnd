package com.tlc.blog.data.repository;

import com.tlc.blog.data.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDeleted(boolean deleted, Pageable pageable);
    List<Post> findByDeletedAndTitleContaining(boolean deleted, String keyword, Pageable pageable);
    Optional<Post> findByIdAndDeleted(Long id, boolean deleted);
}
