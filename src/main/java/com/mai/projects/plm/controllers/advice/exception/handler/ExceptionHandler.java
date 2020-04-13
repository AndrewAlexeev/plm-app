package com.mai.projects.plm.controllers.advice.exception.handler;

import com.mai.projects.plm.controllers.AbstractMainController;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.response.ResponseHeader;
import com.mai.projects.plm.model.response.ResponseObject;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends AbstractMainController implements ControllerAdviceExceptionHandler{


    @Override
    public ResponseEntity<ResponseObject<Object>> catchServerException(ServerException ex) {
        ResponseHeader responseHeader= new ResponseHeader();
        ErrorEnum errorEnum = ex.getError();
        responseHeader.setResponseCode(errorEnum.getCode());
        responseHeader.setResponseDate(LocalDateTime.now());
        responseHeader.setResponseMessage(String.format(errorEnum.getMessage(), ex.getParams()));

        return prepareResponseEntity(responseHeader,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public <T> ResponseEntity<ResponseObject<Object>> catchAuthenticationException(AuthenticationException ex) {
        ResponseHeader responseHeader= new ResponseHeader();
        //ErrorEnum errorEnum = ex.getError();
        //responseHeader.setResponseCode(errorEnum.getCode());
//        responseHeader.setResponseMessage(String.format(errorEnum.getMessage(), null));
        //TODO доделать
        return prepareResponseEntity(responseHeader,HttpStatus.UNAUTHORIZED);
    }

    @Override
    public <T> ResponseEntity<ResponseObject<Object>> catchAuthenticationException(ExpiredJwtException ex) {
        ResponseHeader responseHeader= new ResponseHeader();
        //ErrorEnum errorEnum = ex.getError();
        //responseHeader.setResponseCode(errorEnum.getCode());
//        responseHeader.setResponseMessage(String.format(errorEnum.getMessage(), null));
        //TODO доделать
        return prepareResponseEntity(responseHeader,HttpStatus.UNAUTHORIZED);
    }
}
