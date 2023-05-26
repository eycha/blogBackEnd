package com.tlc.blog.service;

import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;
import com.tlc.blog.data.vo.response.LoginResVo;

public interface MemberService {
    LoginResVo login(LoginReqVo loginReqVo);
    void signUp(SignUpReqVo signUpReqVo);
    void list();
    void delete();
}
