package com.TodoApp.springbootCrudApp;

import com.TodoApp.springbootCrudApp.controller.TodoController;
import com.TodoApp.springbootCrudApp.domain.Todo;
import com.TodoApp.springbootCrudApp.model.response.BaseDetailsResponse;
import com.TodoApp.springbootCrudApp.model.response.DefaultResponse;
import com.TodoApp.springbootCrudApp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;

@SpringBootTest
@Slf4j
public class UnitTests {
    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void testGetAllTodos_Success() {
        // Arrange
        HashMap<String, Object> mockData = new HashMap<>();
        mockData.put("key", "value");
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(200, "Success", mockData);
        log.info("mockResponse: " + mockResponse.toString());
        when(todoService.getAllTodos()).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.getAllTodos();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(mockData, responseEntity.getBody().getData());
        verify(todoService, times(1)).getAllTodos();
    }

    @Test
    void testGetAllTodos_Failure() {
        // Arrange
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(400, "Error", null);
        when(todoService.getAllTodos()).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.getAllTodos();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody().getMessage());
        verify(todoService, times(1)).getAllTodos();
    }


    @Test
    void testCreateTodo_Success() {
        // Arrange
        Todo mockData = new Todo();
        BaseDetailsResponse<Todo> mockResponse = new BaseDetailsResponse<>(200, "Success", mockData);
        log.info("mockResponse: " + mockResponse.toString());
        when(todoService.createTodo(any())).thenReturn(mockResponse);

        // Act
        Todo todo = new Todo();
        todo.setId("1");
        todo.setTodo("task2");
        todo.setCompleted(false);
        ResponseEntity<DefaultResponse> responseEntity = todoController.createTodo(todo);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(mockData, responseEntity.getBody().getData());
        verify(todoService, times(1)).createTodo(any());
    }

    @Test
    void testCreateTodo_Failure() {
        // Arrange
        BaseDetailsResponse<Todo> mockResponse = new BaseDetailsResponse<>(400, "Error", null);
        when(todoService.createTodo(any())).thenReturn(mockResponse);

        // Act
        Todo todo = new Todo();
        todo.setId("1");
        todo.setTodo("task2");
        todo.setCompleted(false);
        ResponseEntity<DefaultResponse> responseEntity = todoController.createTodo(todo);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody().getMessage());
        verify(todoService, times(1)).createTodo(any());
    }
    @Test
    void testGetTodoById_Success() {
        // Arrange
        HashMap<String, Object> mockData = new HashMap<>();
        mockData.put("key", "value");
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(200, "Success", mockData);
        log.info("mockResponse: " + mockResponse.toString());
        when(todoService.getTodoById(any())).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.getTodoById("1");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(mockData, responseEntity.getBody().getData());
        verify(todoService, times(1)).getTodoById(any());
    }

    @Test
    void testGetTodoById_Failure() {
        // Arrange
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(400, "Error", null);
        when(todoService.getTodoById(any())).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.getTodoById("1");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody().getMessage());
        verify(todoService, times(1)).getTodoById(any());
    }

    @Test
    void testDeleteTodo_success(){
        // Arrange
        HashMap<String, Object> mockData = new HashMap<>();
        mockData.put("key", "value");
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(200, "Success", mockData);
        log.info("mockResponse: " + mockResponse.toString());
        when(todoService.deleteTodo(any())).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.deleteTodo("1");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(mockData, responseEntity.getBody().getData());
        verify(todoService, times(1)).deleteTodo(any());
    }

    @Test
    void testDeleteTodo_Failure() {
        // Arrange
        BaseDetailsResponse<HashMap<String, Object>> mockResponse = new BaseDetailsResponse<>(400, "Error", null);
        when(todoService.deleteTodo(any())).thenReturn(mockResponse);

        // Act
        ResponseEntity<DefaultResponse> responseEntity = todoController.deleteTodo("1");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody().getMessage());
        verify(todoService, times(1)).deleteTodo(any());
    }
}
