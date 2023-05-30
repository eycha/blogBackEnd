package com.tlc.blog.service.impl;

import com.tlc.blog.data.entity.Member;
import com.tlc.blog.data.entity.Post;
import com.tlc.blog.data.repository.MemberRepository;
import com.tlc.blog.data.repository.PostRepository;
import com.tlc.blog.data.vo.PostAddReqVo;
import com.tlc.blog.data.vo.PostUpdateReqVo;
import com.tlc.blog.data.vo.response.PostListResVo;
import com.tlc.blog.data.vo.response.PostDetailResVo;
import com.tlc.blog.error.Error;
import com.tlc.blog.error.ErrorSpec;
import com.tlc.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Value("${blog.image.base-path}")
    private String basePath;

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    @Override
    public PostListResVo list(Pageable pageable) {
        return new PostListResVo(postRepository.findByDeleted(false, pageable));
    }

    @Transactional(readOnly = true)
    @Override
    public PostDetailResVo detail(Long postId) {
        Post post = postRepository.findByIdAndDeleted(postId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundPost);
                });

        return new PostDetailResVo(post.getId(), post.getTitle(), post.getContent(), post.getImagePath(), post.getCreated(), post.getMember().getId());
    }

    @Transactional(readOnly = true)
    @Override
    public PostListResVo search(String keyword, Pageable pageable) {
        return new PostListResVo(postRepository.findByDeletedAndTitleContaining(false, keyword, pageable));
    }

    @Transactional
    @Override
    public PostListResVo add(Long memberId, PostAddReqVo postAddReqVo, MultipartFile image, Pageable pageable) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundMember);
                });

        if(ObjectUtils.isNotEmpty(image)) {
            try {
                //폴더 확인
                File imageFile = new File(basePath);
                if (!imageFile.exists()) imageFile.mkdirs();

                Post post = new Post(postAddReqVo.getTitle(), postAddReqVo.getContent(), member, false);
                postRepository.save(post);

                String imagePath = imageFile.getAbsolutePath() + "\\" + post.getId() + member.getId() + ".jpg";
                //이미지 저장
                image.transferTo(new File(imagePath));

                post.setImagePath(imagePath);
            } catch (Exception e) {
                throw Error.of(ErrorSpec.SaveImageError);
            }
        }


        return list(pageable);
    }

    @Transactional
    @Override
    public PostListResVo update(Long memberId, Long postId, PostUpdateReqVo postUpdateReqVo, MultipartFile image, Pageable pageable) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundMember);
                });

        Post post = postRepository.findByIdAndDeleted(postId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundPost);
                });

        if(!post.getMember().getId().equals(member.getId())) {
            throw Error.of(ErrorSpec.NoAuthorization);
        }

        if(ObjectUtils.isNotEmpty(image)) {
            try {
                //폴더 확인
                File imageFile = new File(basePath);
                if (!imageFile.exists()) imageFile.mkdirs();

                post.setTitle(postUpdateReqVo.getTitle());
                post.setContent(postUpdateReqVo.getContent());

                String imagePath = imageFile.getAbsolutePath() + "\\" + post.getId() + member.getId() + ".jpg";
                //기존 이미지 삭제
                File file = new File(post.getImagePath());
                if(file.exists()) file.delete();

                //이미지 저장
                image.transferTo(new File(imagePath));

                post.setImagePath(imagePath);
            } catch (Exception e) {
                throw Error.of(ErrorSpec.SaveImageError);
            }
        }

        return list(pageable);
    }

    @Transactional
    @Override
    public void delete(Long memberId, Long postId) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundMember);
                });

        Post post = postRepository.findByIdAndDeleted(postId, false).orElseThrow(() -> {
                    throw Error.of(ErrorSpec.NotFoundPost);
                });

        if(!post.getMember().getId().equals(member.getId())) {
            throw Error.of(ErrorSpec.NoAuthorization);
        }

        post.setDeleted(true);
    }

}
