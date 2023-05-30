package com.tlc.blog.service;

import com.tlc.blog.data.vo.GuestBookAddReqVo;
import com.tlc.blog.data.vo.GuestBookUpdateReqVo;
import com.tlc.blog.data.vo.response.GuestBookListResVo;

public interface GuestBookService {
    GuestBookListResVo list();
    GuestBookListResVo add(Long memberId, GuestBookAddReqVo guestBookAddReqVo);
    GuestBookListResVo update(Long memberId, Long guestBookId, GuestBookUpdateReqVo guestBookUpdateReqVo);
    void delete(Long memberId, Long guestBookId);
}
