package com.example.market2.exception;

import com.example.market2.response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServerException extends  Exception{
    private final ResponseCode responseCode;
    private final String message;
}
