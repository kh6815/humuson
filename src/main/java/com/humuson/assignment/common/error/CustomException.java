package com.humuson.assignment.common.error;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private ExceptionCode exceptionCode = null;
    private Object exceptionData = null;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getResultMessage());
        this.exceptionCode = exceptionCode;
    }

    public CustomException(ExceptionCode exceptionCode, Object exceptionData) {
        super(exceptionCode.getResultMessage());
        this.exceptionCode = exceptionCode;
        this.exceptionData = exceptionData;
    }
}
