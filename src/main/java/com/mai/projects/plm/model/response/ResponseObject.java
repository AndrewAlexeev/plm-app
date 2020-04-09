package com.mai.projects.plm.model.response;

import lombok.Data;

@Data
public class ResponseObject<T> {
    private ResponseHeader responseHeader;
    private T responseBody;
}
