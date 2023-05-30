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

    public Post(String title, String content, Member member, boolean deleted) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.deleted = deleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
