package com.TodoApp.springbootCrudApp.controller;


import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        if(todoService.getAllTodos().size() > 0){
            return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
        } else{
            return new ResponseEntity("No todo task available", HttpStatus.NOT_FOUND);
        }
    }

      @PostMapping("/todos")
      public ResponseEntity<?> createTodo(@RequestBody Todo todos){
        try{
            todos.setCreatedAt(new Date(System.currentTimeMillis()));
            return new ResponseEntity<Todo>(todoService.createTodo(todos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }


    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todos){
        return new ResponseEntity<>(todoService.updateTodo(id, todos), HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable String id){
        try{
            todoService.deleteTodo(id);
            System.out.println("Successfully deleted :" +id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
