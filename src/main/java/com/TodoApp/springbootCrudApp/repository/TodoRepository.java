package com.TodoApp.springbootCrudApp.repository;
import com.TodoApp.springbootCrudApp.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
}
