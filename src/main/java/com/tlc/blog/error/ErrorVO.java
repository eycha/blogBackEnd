package com.tlc.blog.error;

import com.tlc.blog.error.Error.BaseCodeException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Slf4j
public class ErrorVO {

    @Schema(description = "error http status code")
    private int code;
    @Schema(description = "error spec name")
    private String name;
    @Schema(description = "error message")
    private String txt;

    public static ErrorVO of(BaseCodeException e) {
        return ErrorVO.builder()
                .code(e.getHttpStatus().value())
                .name(e.getErrorSpec())
                .txt(e.getMessage())
                .build();
    }

    public static ErrorVO of(Exception e) {
        return ErrorVO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .name(e.getClass().getName())
                .txt(e.getMessage())
                .build();
    }

}