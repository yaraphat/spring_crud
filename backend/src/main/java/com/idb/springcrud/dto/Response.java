package com.idb.springcrud.dto;

import lombok.Data;

@Data
public class Response {
    private Object body;
    private Object header;
    private String statusCode;
    private String message;

}
