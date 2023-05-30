package com.tlc.blog.data.vo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostListResVo {
    private Long postId;
    private String title;
    private String content;
    private String imagePath;
    private Long writerId;

    public PostListResVo(Long postId, String title, String content, String imagePath, Long writerId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.writerId = writerId;
    }
}
