package com.cache.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DistributedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedApplication.class, args);
	}

}
