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

//    @PostMapping("/todos")
//    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
//        try{
//            todo.setCreatedAt(new Date(System.currentTimeMillis()));
//            todoRepo.save(todo);
//            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
//        } catch (Exception e) {
//            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }

//    @GetMapping("/todos/{id}")
//    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
//       Optional<Todo> todoOptional =  todoRepo.findById(id);
//       if(todoOptional.isPresent()) {
//           return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
//       } else {
//           return new ResponseEntity<>("Todo not found with id" +id, HttpStatus.NOT_FOUND);
//       }
//    }


//    @PutMapping("/todos/{id}")
//    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) {
//        Optional<Todo> todoOptional = todoRepo.findById(id);
//        if(todoOptional.isPresent()) {
//            Todo todoToSave = todoOptional.get();
//            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
//            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
//            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
//            todoRepo.save(todoToSave);
//            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Todo not found with id" +id, HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable String id){
        try{
            todoService.deleteTodo(id);
            System.out.println("Successfully deleted :" +id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
//    @CrossOrigin
//    @DeleteMapping("/todos/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
//        try{
//            todoRepo.deleteById(id);
//            return new ResponseEntity<>("Successfully deleted :"+id, HttpStatus.OK);
//    }catch(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

}
