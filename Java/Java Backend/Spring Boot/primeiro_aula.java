package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	public void run(String... args) throws Exception {
		double a = 5;
		double b = 5;
		double soma = a+b;
		System.out.println("Ola, Mundo!");
		System.out.println(soma);

	}
}
