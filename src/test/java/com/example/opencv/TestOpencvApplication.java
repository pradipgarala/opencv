package com.example.opencv;

import org.springframework.boot.SpringApplication;

public class TestOpencvApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
