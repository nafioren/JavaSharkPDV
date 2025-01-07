package com.proyecto.JavaSharkPDV;

import org.springframework.boot.SpringApplication;

public class TestJavaSharkPdvApplication {

	public static void main(String[] args) {
		SpringApplication.from(JavaSharkPdvApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
