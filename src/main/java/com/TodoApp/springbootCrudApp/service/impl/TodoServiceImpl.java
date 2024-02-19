package com.TodoApp.springbootCrudApp.service.impl;

import com.TodoApp.springbootCrudApp.constant.ExceptionMessages;
import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.exception.NotFoundException;
import com.TodoApp.springbootCrudApp.model.response.BaseDetailsResponse;
import com.TodoApp.springbootCrudApp.repository.TodoRepository;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public TodoServiceImpl(TodoRepository todoRepository, MongoTemplate mongoTemplate) {
        this.todoRepository = todoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public BaseDetailsResponse<Todo> createTodo(Todo todos) {
        Todo existing = findExisting(todos.getTodo());
        try {
            Todo.builder()
                    .todo(todos.getTodo())
                    .completed(todos.getCompleted())
                    .build();

            if(existing != null) {
                return BaseDetailsResponse.<Todo>builder()
                        .code(400)
                        .message("Todo already exists")
                        .build();
            }else {

                todoRepository.save(todos);

                return BaseDetailsResponse.<Todo>builder()
                        .code(200)
                        .message("Created")
                        .data(todos)
                        .build();

            }
        } catch (Exception e) {
            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
        }
    }

    @Override
    public Todo updateTodo(String id, Todo todos) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoSave = todoOptional.get();
            todoSave.setTodo(todos.getTodo() != null ? todos.getTodo() : todoSave.getTodo());
            todoSave.setCompleted(todos.getCompleted() != null ? todos.getCompleted() : todoSave.getCompleted());
//            todoSave.setUpdatedDateTime(new LocalDateTime(System.currentTimeMillis()));
            return todoRepository.save(todoSave);
        } else {
            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
        }
    }

    @Override
    public BaseDetailsResponse<HashMap<String,Object>> deleteTodo(String id) {
        try {
            todoRepository.deleteById(id);

            return BaseDetailsResponse.<HashMap<String, Object>>builder()
                    .code(200)
                    .message("Deleted")
                    .build();
        } catch (Exception e) {
            return BaseDetailsResponse.<HashMap<String, Object>>builder()
                    .code(500)
                    .message("Error")
                    .build();
        }
    }

    @Override
    public BaseDetailsResponse<HashMap<String, Object>> getAllTodos() {
        try {
            List<Todo> todoList = todoRepository.findAll();
            if (!todoList.isEmpty()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("sent_requests", todoList);

                return BaseDetailsResponse.<HashMap<String, Object>>builder()
                        .code(200)
                        .message("Get all list success")
                        .data(data)
                        .build();
            } else {
                return BaseDetailsResponse.<HashMap<String, Object>>builder()
                        .code(404)
                        .message(ExceptionMessages.TODO_DOES_NOT_EXISTS)
                        .build();
            }
        } catch (Exception e) {
//            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
            return BaseDetailsResponse.<HashMap<String, Object>>builder()
                    .code(500)
                    .message("No data")
                    .build();
        }
    }


    @Override
    public BaseDetailsResponse<HashMap<String,Object>> getTodoById(String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("sent_requests", todoOptional.get());
            return BaseDetailsResponse.<HashMap<String, Object>>builder()
                    .code(200)
                    .message("Get by id success")
                    .data(data)
                    .build();
        } else {
            throw new NotFoundException(ExceptionMessages.TODO_DOES_NOT_EXISTS);
        }
    }

    public Todo findExisting(String uniqueField) {
        Query query = new Query(Criteria.where("todo").is(uniqueField)).limit(1);
        return mongoTemplate.findOne(query, Todo.class);
    }


}

