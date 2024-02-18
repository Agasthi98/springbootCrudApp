package com.TodoApp.springbootCrudApp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDetailsResponse<T> {
    private int code;
    private String message;
    private T data;
}
