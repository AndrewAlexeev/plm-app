package com.mai.projects.plm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PlmApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlmApplication.class, args);
		log.info("start");
	}
}
