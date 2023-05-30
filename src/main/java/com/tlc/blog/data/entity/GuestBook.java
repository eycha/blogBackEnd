package com.tlc.blog.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GuestBook extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    @Column(columnDefinition="varchar(50)")
    private String content;

    private boolean deleted;

    public GuestBook(Member member, String content, boolean deleted) {
        this.member = member;
        this.content = content;
        this.deleted = deleted;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
