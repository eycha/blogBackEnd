package com.tlc.blog.controller;

import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;
import com.tlc.blog.response.Response;
import com.tlc.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    //로그인
    @PostMapping("/login")
    public Callable<Response<Void>> login(@RequestBody LoginReqVo loginReqVo) {
        memberService.login(loginReqVo);
        return () -> Response.OK;
    }

    //회원가입
    @PostMapping("/signUp")
    public Callable<Response<Void>> signUp(@RequestBody SignUpReqVo signUpReqVo) {
        memberService.signUp(signUpReqVo);
        return () -> Response.OK;
    }

    //회원목록조회
    @GetMapping("")
    public Callable<Response<Void>> list() {
        return null;
    }

    //회원삭제
    @DeleteMapping("/{id}")
    public Callable<Response<Void>> delete() {
        return null;
    }
}
