package com.tlc.blog.controller;

import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;
import com.tlc.blog.data.vo.response.LoginResVo;
import com.tlc.blog.data.vo.response.MemberListResVo;
import com.tlc.blog.response.Response;
import com.tlc.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    //로그인
    @PostMapping("/login")
    public Callable<Response<LoginResVo>> login(@Valid @RequestBody LoginReqVo loginReqVo) {
        return () -> Response.of(memberService.login(loginReqVo));
    }

    //회원가입
    @PostMapping("/signUp")
    public Callable<Response<Void>> signUp(@Valid @RequestBody SignUpReqVo signUpReqVo) {
        memberService.signUp(signUpReqVo);
        return () -> Response.OK;
    }

    //회원목록조회
    @GetMapping("")
    public Callable<Response<MemberListResVo>> list(@RequestHeader Long memberId) {
        return () -> Response.of(memberService.list(memberId));
    }

    //회원삭제
    @DeleteMapping("/{id}")
    public Callable<Response<Void>> delete(@RequestHeader Long memberId, @PathVariable Long deleteMemberId) {
        memberService.delete(memberId,deleteMemberId);
        return () -> Response.OK;
    }
}
