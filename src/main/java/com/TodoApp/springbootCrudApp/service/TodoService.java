package com.TodoApp.springbootCrudApp.service;

import com.TodoApp.springbootCrudApp.domain.Todo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todos);
    Todo updateTodo(String id, Todo todos);
    Void deleteTodo(String id);
    List<Todo> getAllTodos();

}
