package com.tlc.blog.error;

import com.tlc.blog.error.Error.BaseCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(BaseCodeException.class)
    public ResponseEntity<ErrorVO> handleBaseCodeException(BaseCodeException exception) {
        print("BaseCode ErrorHandler: ", exception);
        final ErrorVO error = ErrorVO.of(exception);
        return ResponseEntity.status(HttpStatus.valueOf(error.getCode())).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorVO> handleNotFoundException(NoHandlerFoundException exception) {
        print("Not Found ErrorHandler: ", exception);
        final BaseCodeException notFoundException = Error.of(ErrorSpec.NotFoundError);
        final ErrorVO error = ErrorVO.of(notFoundException);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVO> handleAllExceptions(Exception exception) {
        print("ErrorHandler: ", exception);
        final ErrorVO error = ErrorVO.of(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private void print(String format, Exception e) {
        log.error(format, e);
    }
}
