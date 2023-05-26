package com.tlc.blog.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class Response<T> {

    private int code;
    private String txt;
    private T body;

    public static final Response<Void> OK = new Response<>();

    private Response() {
        this.code = ResponseCode.OK.getCode();
        this.txt = ResponseCode.OK.getMessage();
    }

    private Response(T body) {
        this();
        this.body = body;
    }

    public static <T> Response<T> of(T body) {
        Response<T> response = new Response<>(body);

        return response;
    }
}
