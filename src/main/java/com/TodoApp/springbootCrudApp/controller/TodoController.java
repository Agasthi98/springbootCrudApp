package com.TodoApp.springbootCrudApp.controller;


import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.repository.TodoRepository;
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

    @Autowired
    private TodoRepository todoRepo;

    @CrossOrigin
    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<Todo> todos = todoRepo.findAll();
        if(todos.size() > 0){
            return  new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No todos Available", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        try{
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
       Optional<Todo> todoOptional =  todoRepo.findById(id);
       if(todoOptional.isPresent()) {
           return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
       } else {
           return new ResponseEntity<>("Todo not found with id" +id, HttpStatus.NOT_FOUND);
       }
    }

    @CrossOrigin
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) {
        Optional<Todo> todoOptional = todoRepo.findById(id);
        if(todoOptional.isPresent()) {
            Todo todoToSave = todoOptional.get();
            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToSave);
            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try{
            todoRepo.deleteById(id);
            return new ResponseEntity<>("Successfully deleted :"+id, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
