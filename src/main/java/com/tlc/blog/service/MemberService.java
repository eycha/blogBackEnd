package com.tlc.blog.service;

import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;
import com.tlc.blog.data.vo.response.LoginResVo;
import com.tlc.blog.data.vo.response.MemberListResVo;

public interface MemberService {
    LoginResVo login(LoginReqVo loginReqVo);
    void signUp(SignUpReqVo signUpReqVo);
    MemberListResVo list(Long memberId);
    void delete(Long memberId, Long deleteMemberId);
}
