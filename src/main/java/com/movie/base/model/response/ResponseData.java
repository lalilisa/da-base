package com.movie.base.model.response;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseData<T> {
    private boolean isError = true;
    private Integer statusCode;
    private T data;
}
