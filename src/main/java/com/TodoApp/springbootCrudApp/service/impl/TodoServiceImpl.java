package com.TodoApp.springbootCrudApp.service.impl;

import com.TodoApp.springbootCrudApp.constant.ExceptionMessages;
import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.exception.NotFoundException;
import com.TodoApp.springbootCrudApp.repository.TodoRepository;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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
            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
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
            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
        }
    }

}

