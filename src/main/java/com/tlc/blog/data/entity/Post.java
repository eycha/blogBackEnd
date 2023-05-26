package com.tlc.blog.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    private String imagePath;

    private boolean deleted;
}
