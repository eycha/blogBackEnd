package com.tlc.blog.data.repository;

import org.aspectj.weaver.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
