package com.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiDatabaseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatabaseAppApplication.class, args);
		System.out.println("multi");
	}

}
