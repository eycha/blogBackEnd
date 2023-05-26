package com.tlc.blog.data.entity;


import com.tlc.blog.data.enumerate.Authorization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String userPw;

    @Enumerated(EnumType.STRING)
    private Authorization authorization;

    private boolean deleted;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "member")
    private List<GuestBook> guestBooks;

    public Member(String userId, String userPw, Authorization authorization, boolean deleted) {
        this.userId = userId;
        this.userPw = userPw;
        this.authorization = authorization;
        this.deleted = deleted;
    }
}
