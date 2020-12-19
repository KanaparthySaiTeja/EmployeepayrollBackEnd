package com.cg.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private String message;
    private String statusCode;
    private Object data;

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
