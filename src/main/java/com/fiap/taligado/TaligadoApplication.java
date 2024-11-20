package com.fiap.taligado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan
@ComponentScan
@SpringBootApplication
public class TaligadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaligadoApplication.class, args);
	}

}
