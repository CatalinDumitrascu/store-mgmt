package com.ingexample.storemgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StoreMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreMgmtApplication.class, args);
	}

}
