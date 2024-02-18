package com.TodoApp.springbootCrudApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SpringbootCrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudAppApplication.class, args);
	}

}
