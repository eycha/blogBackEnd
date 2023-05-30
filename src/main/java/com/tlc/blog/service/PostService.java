package com.tlc.blog.service;

import com.tlc.blog.data.vo.PostAddReqVo;
import com.tlc.blog.data.vo.PostUpdateReqVo;
import com.tlc.blog.data.vo.response.PostListResVo;
import com.tlc.blog.data.vo.response.PostDetailResVo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostListResVo list(Pageable pageable);
    PostDetailResVo detail(Long postId);
    PostListResVo search(String keyword, Pageable pageable);
    PostListResVo add(Long memberId, PostAddReqVo postAddReqVo, MultipartFile image, Pageable pageable);
    PostListResVo update(Long memberId, Long postId, PostUpdateReqVo postUpdateReqVo, MultipartFile image, Pageable pageable);
    void delete(Long memberId, Long postId);
}
