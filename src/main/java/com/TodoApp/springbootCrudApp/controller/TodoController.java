package com.TodoApp.springbootCrudApp.controller;


import com.TodoApp.springbootCrudApp.constant.Api;
import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Api.API_BASE_URL + Api.TODO_PATH)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getAllTodos() {
        if(todoService.getAllTodos().size() > 0){
            return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
        } else{
            return new ResponseEntity("No todo task available", HttpStatus.NOT_FOUND);
        }
    }

      @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> createTodo(@RequestBody Todo todos){
        try{
            todos.setCreatedAt(new Date(System.currentTimeMillis()));
            return new ResponseEntity<Todo>(todoService.createTodo(todos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }

    @GetMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }


    @PutMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todos){
        return new ResponseEntity<>(todoService.updateTodo(id, todos), HttpStatus.OK);
    }

    @DeleteMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTodo(@PathVariable String id){
        try{
            todoService.deleteTodo(id);
            System.out.println("Successfully deleted : " +id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
