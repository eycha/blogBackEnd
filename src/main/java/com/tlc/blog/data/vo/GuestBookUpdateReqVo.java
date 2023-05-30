package com.tlc.blog.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class GuestBookUpdateReqVo {

    @NotNull
    private String content;
}
