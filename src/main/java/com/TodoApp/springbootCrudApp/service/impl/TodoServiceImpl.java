package com.TodoApp.springbootCrudApp.service.impl;

import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.repository.TodoRepository;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo createTodo(Todo todos) {
        return null;
    }

    @Override
    public Todo updateTodo(String id, Todo todos) {
        return null;
    }

    @Override
    public Void deleteTodo(String id) {
        return null;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
