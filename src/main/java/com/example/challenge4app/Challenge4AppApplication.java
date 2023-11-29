// src/main/java/com/example/demo/DemoApplication.java

package com.example.challenge4app;

import com.example.challenge4app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Challenge4AppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Challenge4AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Checking database connection...");

		// Perform a simple database operation to check the connection
		userRepository.findAll();

		System.out.println("Database connection is successful.");
	}
}
