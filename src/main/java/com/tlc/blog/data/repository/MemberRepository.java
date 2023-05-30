package com.tlc.blog.data.repository;

import com.tlc.blog.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserIdAndUserPwAndDeleted(String userId, String userPw, boolean deleted);
    Optional<Member> findByUserIdAndDeleted(String userId, boolean deleted);
    Optional<Member> findByIdAndDeleted(Long id, boolean deleted);
    List<Member> findByDeleted(boolean deleted);
}
