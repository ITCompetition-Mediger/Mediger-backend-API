package com.cos.mediAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
public class MedigerBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedigerBackendApiApplication.class, args);
	}

}
