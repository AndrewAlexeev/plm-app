package com.mai.projects.plm.exception;

import com.mai.projects.plm.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    ErrorEnum error;
    private List<String> params;
}
