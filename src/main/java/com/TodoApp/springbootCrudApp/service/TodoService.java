package com.TodoApp.springbootCrudApp.service;

import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.model.request.TodoRequest;
import com.TodoApp.springbootCrudApp.model.response.BaseDetailsResponse;

import java.util.HashMap;
import java.util.Objects;

public interface TodoService {
    BaseDetailsResponse<Todo> createTodo(Todo todos);

    Todo updateTodo(String id, Todo todos);

    BaseDetailsResponse<HashMap<String,Object>> deleteTodo(String id);

//    List<Todo> getAllTodos();
    BaseDetailsResponse<HashMap<String, Object>> getAllTodos();

    BaseDetailsResponse<HashMap<String,Object>> getTodoById(String id);

}