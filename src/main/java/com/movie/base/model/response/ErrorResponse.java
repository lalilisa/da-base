package com.movie.base.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data

public class ErrorResponse {
    private boolean isError = false;
    private Integer statusCode;
    private Map<String,String> errorMap;

    public ErrorResponse(Integer statusCode,String message){
        this.statusCode = statusCode;
        errorMap = new HashMap<>();
        errorMap.put("message",message);
    }
    public ErrorResponse(Integer statusCode,Map<String,String> errorMap){
        this.statusCode = statusCode;
        this.errorMap =errorMap;
    }


}
