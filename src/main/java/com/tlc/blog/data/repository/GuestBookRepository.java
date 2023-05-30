package com.tlc.blog.data.repository;

import com.tlc.blog.data.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

    List<GuestBook> findByDeleted(boolean deleted);
    Optional<GuestBook> findByIdAndDeleted(Long id, boolean deleted);
}
