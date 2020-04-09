package com.mai.projects.plm.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    USER_BY_USERNAME_NOT_FOUND("user by username = %s not found", "1"),
    USERS_NOT_FOUND("users not found ", "2"),
    USER_NOT_REGISTERED("user not registered", "3"),
    ROLE_NOT_FOUND("user role not found","4"),
    USERNAME_OR_EMAIL_ARE_ALWAYS_EXIST("данные username или email уже существует","5");

    private String message;
    private String code;

    ErrorEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
