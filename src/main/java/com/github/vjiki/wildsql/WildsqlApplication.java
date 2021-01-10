package com.github.vjiki.wildsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
public class WildsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(WildsqlApplication.class, args);
	}

}
