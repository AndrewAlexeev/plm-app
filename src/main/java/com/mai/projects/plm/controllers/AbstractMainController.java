package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.response.ResponseHeader;
import com.mai.projects.plm.model.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class AbstractMainController {

    ResponseEntity prepareResponseEntity(){
        ResponseEntity responseEntity= new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    ResponseHeader prepareResponseHeader(){
        ResponseHeader header= new ResponseHeader();
        header.setResponseDate(LocalDateTime.now());
        return header;
    }

    ResponseHeader prepareResponseHeader(String code, String message){
        ResponseHeader header= prepareResponseHeader();
        header.setResponseMessage(message);
        header.setResponseCode(code);
        return header;
    }
    public <T> ResponseEntity<ResponseObject<T>> prepareResponseEntity(T responseBody){
        ResponseHeader responseHeader = prepareResponseHeader();
        return prepareResponseEntity(responseBody, responseHeader, HttpStatus.OK);
    }

    public <T> ResponseEntity<ResponseObject<T>> prepareEmptyResponseEntity(){
        ResponseHeader responseHeader = prepareResponseHeader();
        return prepareResponseEntity(null, responseHeader, HttpStatus.OK);
    }

    public <T> ResponseEntity<ResponseObject<T>> prepareResponseEntity( ResponseHeader responseHeader, HttpStatus httpStatus){
        return prepareResponseEntity(null, responseHeader, httpStatus);
    }

    public <T> ResponseEntity<ResponseObject<T>> prepareResponseEntity(T responseBody, ResponseHeader responseHeader, HttpStatus httpStatus){
        ResponseObject<T> responseObject =new ResponseObject<>();
        responseObject.setResponseHeader(responseHeader);
        responseObject.setResponseBody(responseBody);
        return ResponseEntity.status(httpStatus).body(responseObject);
    }
}
