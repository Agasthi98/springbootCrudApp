package com.TodoApp.springbootCrudApp.controller;


import com.TodoApp.springbootCrudApp.constant.Api;
import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.model.response.BaseDetailsResponse;
import com.TodoApp.springbootCrudApp.model.response.DefaultResponse;
import com.TodoApp.springbootCrudApp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping(Api.API_BASE_URL + Api.TODO_PATH)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> getAllTodos() {
        BaseDetailsResponse<HashMap<String, Object>> response = todoService.getAllTodos();
        if(response.getCode() == 200){
            return ResponseEntity.ok(DefaultResponse.success(response.getMessage(), response.getData()));
        } else{
            return ResponseEntity.badRequest().body(DefaultResponse.error(response.getMessage()));
        }
    }

      @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<DefaultResponse> createTodo(@RequestBody Todo todos){
            BaseDetailsResponse<Todo> response = todoService.createTodo(todos);
            if(response.getCode() == 200){
                return ResponseEntity.ok(DefaultResponse.success(response.getMessage(), response.getData()));
            } else{
                return ResponseEntity.badRequest().body(DefaultResponse.error(response.getMessage()));
            }
      }

    @GetMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> getTodoById(@PathVariable String id){
        BaseDetailsResponse<HashMap<String,Object>> response = todoService.getTodoById(id);
        if(response.getCode() == 200){
            return ResponseEntity.ok(DefaultResponse.success(response.getMessage(), response.getData()));
        } else{
            return ResponseEntity.badRequest().body(DefaultResponse.error(response.getMessage()));
        }
    }


    @PutMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todos){
        return new ResponseEntity<>(todoService.updateTodo(id, todos), HttpStatus.OK);
    }

    @DeleteMapping(path = Api.TODO_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> deleteTodo(@PathVariable String id){
        BaseDetailsResponse<HashMap<String,Object>> response = todoService.deleteTodo(id);
        if(response.getCode() == 200){
            return ResponseEntity.ok(DefaultResponse.success(response.getMessage(), response.getData()));
        } else{
            return ResponseEntity.badRequest().body(DefaultResponse.error(response.getMessage()));
        }

    }

}
