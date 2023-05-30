package com.tlc.blog.data.vo.response;

import com.tlc.blog.data.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostListResVo {
    Page<PostItem> postItems;

    @Getter
    @NoArgsConstructor
    private static class PostItem {
        private Long postId;
        private String title;
        private String content;
        private String imagePath;
        private Long writerId;

        public PostItem(Long postId, String title, String content, String imagePath, Long writerId) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.imagePath = imagePath;
            this.writerId = writerId;
        }
    }


    public PostListResVo(Page<Post> posts) {
        List<PostItem> postItems = new ArrayList<>();
        posts.forEach(postItem -> {
            postItems.add(new PostItem(
                    postItem.getId(),
                    postItem.getTitle(),
                    postItem.getContent(),
                    postItem.getImagePath(),
                    postItem.getMember().getId()));
        });

        this.postItems = new PageImpl<>(postItems, posts.getPageable(), posts.getTotalElements());
    }
}
