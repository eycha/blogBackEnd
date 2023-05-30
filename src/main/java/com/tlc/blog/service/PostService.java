package com.tlc.blog.service;

import com.tlc.blog.data.vo.PostAddReqVo;
import com.tlc.blog.data.vo.PostUpdateReqVo;
import com.tlc.blog.data.vo.response.PostListResVo;
import com.tlc.blog.data.vo.response.PostDetailResVo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<PostListResVo> list(Pageable pageable);
    PostDetailResVo detail(Long postId);
    List<PostListResVo> search(String keyword, Pageable pageable);
    List<PostListResVo> add(Long memberId, PostAddReqVo postAddReqVo, MultipartFile image, Pageable pageable);
    List<PostListResVo> update(Long memberId, Long postId, PostUpdateReqVo postUpdateReqVo, MultipartFile image, Pageable pageable);
    void delete(Long memberId, Long postId);
}
