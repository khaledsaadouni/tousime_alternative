package com.tousime_alternative.exception;

public enum ErrorCodes {


    USER_NOT_FOUND(12000),
    USER_NOT_VALID(12001),
    USER_ALREADY_EXISTS(12002),
    USER_CHANGE_PASSWORD_OBJECT_NOT_VALID(12003),

    BAD_CREDENTIALS(12003);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
