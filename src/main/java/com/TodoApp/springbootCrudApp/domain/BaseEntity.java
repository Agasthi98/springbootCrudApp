package com.TodoApp.springbootCrudApp.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Setter
@Getter
public abstract class BaseEntity {
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
