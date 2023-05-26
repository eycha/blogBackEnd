package com.tlc.blog.service;

import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;

public interface MemberService {
    void login(LoginReqVo loginReqVo);
    void signUp(SignUpReqVo signUpReqVo);
    void list();
    void delete();
}
