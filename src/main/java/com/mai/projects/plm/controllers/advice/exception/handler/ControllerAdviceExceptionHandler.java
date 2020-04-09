package com.mai.projects.plm.controllers.advice.exception.handler;

import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ControllerAdviceExceptionHandler {
    @ExceptionHandler(ServerException.class)
    @ResponseBody
    <T> ResponseEntity<ResponseObject<Object>> catchServerException(ServerException ex);

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    <T> ResponseEntity<ResponseObject<Object>> catchAuthenticationException(AuthenticationException ex);
}
