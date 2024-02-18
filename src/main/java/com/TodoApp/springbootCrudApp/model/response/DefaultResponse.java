package com.TodoApp.springbootCrudApp.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
@Builder
public class DefaultResponse {
    private int code;
    private String message;
    private Object data;


    public DefaultResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DefaultResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = new HashMap<>();
    }

    public static DefaultResponse success( String message, Object data) {
        return new DefaultResponse(200, message, data);
    }

    public static DefaultResponse success( String message) {
        return new DefaultResponse(200, message, new HashMap<String, Object>());
    }

    public static DefaultResponse error( String message, Object data) {
        return new DefaultResponse(400, message, data);
    }

    public static DefaultResponse error( String message) {
        return new DefaultResponse(400, message, new HashMap<String, Object>());
    }

    public static DefaultResponse internalServerError(String title, String message, Object data) {
        return new DefaultResponse(500, message, data);
    }

    public static DefaultResponse internalServerError( String message) {
        return new DefaultResponse(500, message, new HashMap<String, Object>());
    }
}
