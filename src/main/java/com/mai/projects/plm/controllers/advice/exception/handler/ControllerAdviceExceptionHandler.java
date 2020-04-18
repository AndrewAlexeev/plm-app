package com.mai.projects.plm.controllers.advice.exception.handler;

import com.mai.projects.plm.exception.BaseServerException;
import com.mai.projects.plm.model.response.ResponseObject;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ControllerAdviceExceptionHandler {
    @ExceptionHandler(BaseServerException.class)
    @ResponseBody
    <T> ResponseEntity<ResponseObject<Object>> catchServerException(BaseServerException ex);

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    <T> ResponseEntity<ResponseObject<Object>> catchAuthenticationException(AuthenticationException ex);
    @ExceptionHandler(    ExpiredJwtException.class)
    @ResponseBody
    <T> ResponseEntity<ResponseObject<Object>> catchAuthenticationException(ExpiredJwtException ex);
}
