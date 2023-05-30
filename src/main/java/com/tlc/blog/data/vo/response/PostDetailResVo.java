package com.tlc.blog.data.vo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostDetailResVo {
    private Long postId;
    private String title;
    private String content;
    private String imagePath;
    private LocalDateTime created;
    private Long writerId;

    public PostDetailResVo(Long postId, String title, String content, String imagePath, LocalDateTime created, Long writerId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.created = created;
        this.writerId = writerId;
    }
}
