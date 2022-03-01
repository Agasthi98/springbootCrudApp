package com.TodoApp.springbootCrudApp.service.impl;

import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.repository.TodoRepository;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo createTodo(Todo todos) {
        return todoRepository.save(todos);
    }

    @Override
    public Todo updateTodo(String id, Todo todos) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()){
            Todo todoSave = todoOptional.get();
            todoSave.setTodo(todos.getTodo() != null ? todos.getTodo() : todoSave.getTodo());
            todoSave.setCompleted(todos.getCompleted() != null ? todos.getCompleted() : todoSave.getCompleted());
            todoSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            return todoRepository.save(todoSave);
        } else{
            return null;
        }
    }

    @Override
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()){
            return todoOptional.get();
        } else {
            return null;
        }
    }

}

