package com.tlc.blog.data.vo.response;

import com.tlc.blog.data.enumerate.Authorization;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResVo {
    private Long id;
    private Authorization authorization;

    public LoginResVo(Long id, Authorization authorization) {
        this.id = id;
        this.authorization = authorization;
    }
}
