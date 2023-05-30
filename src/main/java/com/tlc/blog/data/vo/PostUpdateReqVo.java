package com.tlc.blog.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostUpdateReqVo {
    @NotNull
    private String title;
    @NotNull
    private String content;
}
