package com.guztaver.nexz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NexzApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexzApplication.class, args);
	}
}
