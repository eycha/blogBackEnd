package com.tlc.blog.data.enumerate;

import lombok.Getter;

@Getter

public enum Authorization {
    ROLE_ADMIN("관리자"),
    ROLE_USER("사용자");

    private final String description;

    Authorization(String description) {
        this.description = description;
    }
}
