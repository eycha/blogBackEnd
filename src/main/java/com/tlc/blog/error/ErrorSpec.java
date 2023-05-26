package com.tlc.blog.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorSpec {
    LoginFailed(UNAUTHORIZED, "일치하는 아이디나 비밀번호가 없습니다."),
    NotFoundError(NOT_FOUND, "존재하지 않는 API 입니다."),

    NotFoundMember(NOT_FOUND, "해당 회원이 존재하지 않습니다."),
    DuplicateUserId(CONFLICT, "이미 존재하는 아이디 입니다."),
    NotFoundPost(NOT_FOUND, "존재하지 않는 게시글 입니다."),
    SaveImageError(INTERNAL_SERVER_ERROR, "이미지 저장에 실패하였습니다."),;


    private final HttpStatus httpStatus;
    private final String message;
}
