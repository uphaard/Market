package com.example.market2.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ResponseCode {
    OK(20000, HttpStatus.OK,"SUCCESS"),
    QTY_EXCEEDED(40600,HttpStatus.NOT_ACCEPTABLE,"QTY OF PRODUCT IS LESS THEN YOUR REQUIREMENT"),
    NOT_FOUND(40009,HttpStatus.NOT_FOUND,"USER /PRODUCT NOT FOUND"),
    INSUFFICIENT_BALANCE(40600,HttpStatus.NOT_ACCEPTABLE,"YOU DON'T HAVE ENOUGH MONEY TO BUY PRODUCT");
    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    private static final Map<Integer, ResponseCode> errorCodeMap = new HashMap<>();

    static {
        for (ResponseCode cc : values()) {
            errorCodeMap.put(cc.getErrorCode(), cc);
        }
    }

    ResponseCode(int errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        errorMessage = message;
        this.httpStatus = httpStatus;
    }
    public static ResponseCode getByErrorCode(int errorCode) {
        return errorCodeMap.get(errorCode);
    }
}
