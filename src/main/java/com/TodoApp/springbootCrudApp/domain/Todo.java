package com.TodoApp.springbootCrudApp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos" )
public class Todo extends BaseEntity {

    private String id;
    private String todo;
    private Boolean completed;
}
