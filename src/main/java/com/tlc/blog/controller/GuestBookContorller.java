package com.tlc.blog.controller;


import com.tlc.blog.data.vo.GuestBookAddReqVo;
import com.tlc.blog.data.vo.GuestBookUpdateReqVo;
import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.PostAddReqVo;
import com.tlc.blog.data.vo.response.GuestBookListResVo;
import com.tlc.blog.response.Response;
import com.tlc.blog.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestBook")
public class GuestBookContorller {

    private final GuestBookService guestBookService;

    @GetMapping("")
    public Callable<Response<GuestBookListResVo>> list() {
        return () -> Response.of(guestBookService.list());
    }

    @PostMapping("")
    public Callable<Response<GuestBookListResVo>> add(
            @RequestHeader Long memberId,
            @Valid @RequestBody GuestBookAddReqVo guestBookAddReqVo
    ) {
        return () -> Response.of(guestBookService.add(memberId, guestBookAddReqVo));
    }

    @PutMapping("/{id}")
    public Callable<Response<GuestBookListResVo>> update(@RequestHeader Long memberId,
                                           @PathVariable Long guestBookId,
                                           @Valid @RequestBody GuestBookUpdateReqVo guestBookUpdateReqVo) {
        return () -> Response.of(guestBookService.update(memberId,guestBookId,guestBookUpdateReqVo));
    }

    @DeleteMapping("/{id}")
    public Callable<Response<Void>> delete(@RequestHeader Long memberId,
                                           @PathVariable Long guestBookId) {
        guestBookService.delete(memberId, guestBookId);
        return () -> Response.OK;
    }
}
