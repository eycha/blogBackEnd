package com.tlc.blog.controller;

import com.tlc.blog.data.vo.PostAddReqVo;
import com.tlc.blog.data.vo.PostUpdateReqVo;
import com.tlc.blog.data.vo.response.PostDetailResVo;
import com.tlc.blog.data.vo.response.PostListResVo;
import com.tlc.blog.response.Response;
import com.tlc.blog.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    //게시글 목록 조회
    @GetMapping("")
    public Callable<Response<PostListResVo>> list(
            @Parameter Pageable pageable
    ) {
        return () -> Response.of(postService.list(pageable));
    }

    //게시글 상세 조회
    @GetMapping("/{id}")
    public Callable<Response<PostDetailResVo>> detail(
            @PathVariable Long postId
    ) {
        return () -> Response.of(postService.detail(postId));
    }

    //게시글 검색
    @GetMapping("/search")
    public Callable<Response<PostListResVo>> search(
            @RequestParam(required = false) String keyword,
            @Parameter Pageable pageable
    ) {
        return () -> Response.of(postService.search(keyword, pageable));
    }

    //게시글 등록
    @PostMapping("")
    public Callable<Response<PostListResVo>> add(
            @RequestHeader Long memberId,
            @Valid @RequestPart PostAddReqVo postAddReqVo,
            @RequestPart(required = false) MultipartFile image,
            @Parameter Pageable pageable
    ) {
        return () -> Response.of(postService.add(memberId, postAddReqVo, image, pageable));
    }

    //게시글 수정
    @PutMapping("/{id}")
    public Callable<Response<PostListResVo>> update(
            @RequestHeader Long memberId,
            @Valid @RequestPart PostUpdateReqVo postUpdateReqVo,
            @RequestPart(required = false) MultipartFile image,
            @PathVariable Long postId,
            @Parameter Pageable pageable
    ) {
        return () -> Response.of(postService.update(memberId, postId, postUpdateReqVo, image, pageable));
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public Callable<Response<Void>> delete(
            @RequestHeader Long memberId,
            @PathVariable Long postId
    ) {
        postService.delete(memberId, postId);
        return () -> Response.OK;
    }
}
